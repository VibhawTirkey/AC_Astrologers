package com.astrocure.astrologer.viewModel;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.astrocure.astrologer.models.requestModels.DeleteRecordingRequestModel;
import com.astrocure.astrologer.models.requestModels.UploadAudioRequestModel;
import com.astrocure.astrologer.models.responseModels.DeleteRecordingResponseModel;
import com.astrocure.astrologer.models.responseModels.FileUploadResponseModel;
import com.astrocure.astrologer.models.responseModels.KidKundliQuestionResponseModel;
import com.astrocure.astrologer.models.responseModels.UploadAudioResponseModel;
import com.astrocure.astrologer.repository.HomeRepository;
import com.astrocure.astrologer.repository.KidsKundliRepository;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class KidsKundliViewModel extends ViewModel {
    private static String fileName = null;
    private final HomeRepository homeRepository;
    private final KidsKundliRepository kidsKundliRepository;
    private final MutableLiveData<Boolean> isRecordingLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isPlayingLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isPauseAudioLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> permissionGrantedLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> recordFileLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> fileDeletedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> totalDurationLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> currentTimeLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> currentPositionLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> audioUploadLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<KidKundliQuestionResponseModel.Datum>> questionListLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<String>> audioListLiveData = new MutableLiveData<>();
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    public KidsKundliViewModel() {
        this.homeRepository = new HomeRepository();
        this.kidsKundliRepository = new KidsKundliRepository();
    }

    @SuppressLint("SimpleDateFormat")
    public void startRecording(Context context, String kidName) {
        if (CheckPermissions(context)) {
            if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
                Toast.makeText(context, "Microphone unsupported", Toast.LENGTH_SHORT).show();
                return;
            }
            fileName = getRecordingFilePath(context, kidName);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                mediaRecorder = new MediaRecorder(context);
            } else {
                mediaRecorder = new MediaRecorder();
            }
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setAudioEncodingBitRate(16 * 44100);
            mediaRecorder.setAudioSamplingRate(44100);
            mediaRecorder.setOutputFile(fileName);
            try {
                mediaRecorder.prepare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mediaRecorder.start();
            isRecordingLiveData.postValue(true);
        } else {
            requestPermission(context);
        }
    }

    public void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
        isRecordingLiveData.postValue(false);
        recordFileLiveData.postValue(fileName);
    }

    public void pauseRecording() {
        mediaRecorder.pause();
        isRecordingLiveData.postValue(false);
    }

    public void resumeRecording() {
        mediaRecorder.resume();
        isRecordingLiveData.postValue(true);
    }

    public void playAudio(String fileName) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
            totalDurationLiveData.postValue(mediaPlayer.getDuration());
            isPlayingLiveData.postValue(true);
            isPauseAudioLiveData.postValue(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mediaPlayer.setOnCompletionListener(mp -> isPlayingLiveData.postValue(false));
    }

    public void getCurrentPosition() {
        currentTimeLiveData.postValue(mediaPlayer.getCurrentPosition());
        currentPositionLiveData.postValue(mediaPlayer.getCurrentPosition() * 100 / mediaPlayer.getDuration());
    }

    public void resumeAudio() {
        mediaPlayer.start();
        isPlayingLiveData.postValue(true);
        isPauseAudioLiveData.postValue(false);
    }

    public void pauseAudio() {
        mediaPlayer.pause();
        isPlayingLiveData.postValue(false);
        isPauseAudioLiveData.postValue(true);
    }

    public void stopAudio() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        isPlayingLiveData.postValue(false);
        isPauseAudioLiveData.postValue(false);
    }

    public void seekAudioPosition(int progress) {
        int newPosition = mediaPlayer.getDuration() * progress / 100;
        mediaPlayer.seekTo(newPosition);
    }

    public boolean CheckPermissions(Context context) {
        int result = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(context, RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(Context context) {
        Dexter.withContext(context).withPermissions(WRITE_EXTERNAL_STORAGE, RECORD_AUDIO).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) {

                } else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                    permissionGrantedLiveData.postValue(false);
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    public void deleteFile() {
        File file = new File(fileName);
        boolean deleted = file.delete();
        fileDeletedLiveData.postValue(deleted);
    }

    public void deletePreviousRecording(String astrologerId, String kidKundliId) {
        kidsKundliRepository.deletePrevRecordings(new DeleteRecordingRequestModel(kidKundliId, astrologerId), new KidsKundliRepository.IDeleteRecord() {
            @Override
            public void onSuccess(DeleteRecordingResponseModel deleteRecordingResponseModel) {

            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    @SuppressLint("SimpleDateFormat")
    public String getRecordingFilePath(Context context, String userName) {
        ContextWrapper contextWrapper = new ContextWrapper(context);
        String timestamp = new SimpleDateFormat("_ddMMyy_kkmm").format(new Date());
        File recordingStorage = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File audioFile = new File(recordingStorage, userName + timestamp + ".mp3");
        return audioFile.getPath();
    }

    public void uploadAudio(String path) {
        homeRepository.uploadFiles(Uri.fromFile(new File(path)), new HomeRepository.IUploadImage() {
            @Override
            public void onSuccess(FileUploadResponseModel responseModel) {
                audioUploadLiveData.postValue(responseModel.getData().get(0));
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public void uploadAudioToQuestion(String astrologerId, String kidsKundliId, String questionId, String recordingUrl) {
        kidsKundliRepository.uploadRecordedAudio(new UploadAudioRequestModel(astrologerId, kidsKundliId, questionId, recordingUrl), new KidsKundliRepository.IAudioUpload() {
            @Override
            public void onSuccess(UploadAudioResponseModel uploadAudioResponseModel) {
                audioListLiveData.postValue(uploadAudioResponseModel.getData().getRecordingUrl());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public void getKidsKundliQuestions(String astrologerId) {
        kidsKundliRepository.getKidsKundliQuestion(astrologerId, new KidsKundliRepository.IKidQuestion() {
            @Override
            public void onSuccess(KidKundliQuestionResponseModel kundliQuestionResponseModel) {
                questionListLiveData.postValue(kundliQuestionResponseModel.getData());
            }

            @Override
            public void onFailure(String throwable) {

            }
        });
    }

    public LiveData<Boolean> getIsRecordingLiveData() {
        return isRecordingLiveData;
    }

    public LiveData<String> getRecordFileLiveData() {
        return recordFileLiveData;
    }

    public LiveData<Boolean> getFileDeletedLiveData() {
        return fileDeletedLiveData;
    }

    public LiveData<Boolean> getPermissionGrantedLiveData() {
        return permissionGrantedLiveData;
    }

    public LiveData<Integer> getTotalDurationLiveData() {
        return totalDurationLiveData;
    }

    public LiveData<Integer> getCurrentPositionLiveData() {
        return currentPositionLiveData;
    }

    public LiveData<Integer> getCurrentTimeLiveData() {
        return currentTimeLiveData;
    }

    public LiveData<Boolean> getIsPlayingLiveData() {
        return isPlayingLiveData;
    }

    public LiveData<String> getAudioUploadLiveData() {
        return audioUploadLiveData;
    }

    public LiveData<List<KidKundliQuestionResponseModel.Datum>> getQuestionListLiveData() {
        return questionListLiveData;
    }

    public LiveData<List<String>> getAudioListLiveData() {
        return audioListLiveData;
    }
}

