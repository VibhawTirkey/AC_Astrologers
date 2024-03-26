package com.astrocure.astrologer.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.adapter.KidsRecordingAdapter;
import com.astrocure.astrologer.adapter.KundliQueNumAdapter;
import com.astrocure.astrologer.databinding.ActivityKidsKundliBinding;
import com.astrocure.astrologer.models.responseModels.KidsKundliInfoResponseModel;
import com.astrocure.astrologer.utils.AppConstants;
import com.astrocure.astrologer.utils.SPrefClient;
import com.astrocure.astrologer.viewModel.KidsKundliViewModel;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class KidsKundliActivity extends AppCompatActivity {
    ActivityKidsKundliBinding binding;
    KidsKundliInfoResponseModel.Datum kidInfo;
    KundliQueNumAdapter numAdapter;
    private KidsKundliViewModel viewModel;
    private boolean isRecord = false, isPlaying = false;
    private Handler handler;
    private Runnable mUpdateSeekbar;
    private String filename = null, questionId;
    private int queNumber = 0;

    @SuppressLint({"SimpleDateFormat", "DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        binding = ActivityKidsKundliBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        viewModel = new ViewModelProvider(this).get(KidsKundliViewModel.class);

        kidInfo = (KidsKundliInfoResponseModel.Datum) getIntent().getSerializableExtra("KID_DETAIL");

        assert kidInfo != null;
        binding.name.append(kidInfo.getFullName());
        binding.fatherName.append(kidInfo.getFatherName());
        binding.motherName.append(kidInfo.getMotherName());
        try {
            Date birthTime = new SimpleDateFormat("kk:mm:ss").parse(kidInfo.getTob());
            Date birthDate = new SimpleDateFormat(AppConstants.SERVER_TIME_FORMAT).parse(kidInfo.getDob());
            assert birthTime != null;
            binding.tob.append(new SimpleDateFormat("hh:mm a").format(birthTime));
            assert birthDate != null;
            binding.dob.append(new SimpleDateFormat("dd/MM/yyyy").format(birthDate));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        binding.pob.append(kidInfo.getLocation().getCityName());

        viewModel.deletePreviousRecording(SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), kidInfo.getId());
        viewModel.getKidsKundliQuestions(SPrefClient.getAstrologerDetail(getApplicationContext()).getId());
        numAdapter = new KundliQueNumAdapter(getApplicationContext(), new ArrayList<>());
        viewModel.getQuestionListLiveData().observe(this, data -> {
            numAdapter = new KundliQueNumAdapter(getApplicationContext(), data);
            binding.questionNumList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 6, LinearLayoutManager.VERTICAL, false));
            binding.questionNumList.setAdapter(numAdapter);
        });

        numAdapter.setOnItemActionListener((questionId, questionNum, question) -> {
            binding.question.setText(question);
            binding.questionNum.setText("Question-" + questionNum + 1);
            queNumber = questionNum;
            this.questionId = questionId;
        });
        binding.submitAnswer.setOnClickListener(v -> {
            numAdapter.setItemSelected(queNumber + 1);
        });

        KidsRecordingAdapter recordingAdapter = new KidsRecordingAdapter(getApplicationContext(), new ArrayList<>());
        binding.recordingList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.recordingList.setAdapter(recordingAdapter);
        recordingAdapter.setRecordings(new ArrayList<>());

        binding.record.setOnClickListener(v -> {
            if (isRecord) {
                viewModel.stopRecording();
            } else {
                viewModel.startRecording(getApplicationContext(), kidInfo.getFullName());
            }
        });

        binding.audioDelete.setOnClickListener(v -> viewModel.deleteFile());

        handler = new Handler();
        mUpdateSeekbar = new Runnable() {
            @Override
            public void run() {
                viewModel.getCurrentPosition();
                handler.postDelayed(this, 10);
            }
        };
        binding.playPause.setOnClickListener(v -> {
            if (isPlaying) {
                viewModel.pauseAudio();
            } else {
                viewModel.resumeAudio();
            }
        });
        binding.audioProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    viewModel.seekAudioPosition(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (isPlaying) {
                    viewModel.pauseAudio();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                viewModel.resumeAudio();
            }
        });
        binding.audioSend.setOnClickListener(v -> {
            if (filename != null) {
                viewModel.uploadAudio(filename);
            }
        });
        binding.send.setOnClickListener(v -> {

        });

        viewModel.getAudioListLiveData().observe(this, newList -> {
            binding.recordingList.setVisibility(newList.isEmpty() ? View.GONE : View.VISIBLE);
            recordingAdapter.addRecordings(newList);
        });

        viewModel.getIsRecordingLiveData().observe(this, isRecording -> {
            isRecord = isRecording;
            binding.recordLabel.setText(isRecording ? "Recording..." : "Tap here to record");
            binding.audioPrevContainer.setVisibility(isRecording ? View.GONE : View.VISIBLE);
            binding.record.setCardBackgroundColor(isRecording ? ContextCompat.getColor(getApplicationContext(), R.color.white) : ContextCompat.getColor(getApplicationContext(), R.color.bg_white));
            Glide.with(getApplicationContext()).load(isRecording ? R.drawable.ic_pause : R.drawable.ic_mic_chat).into(binding.recImg);
        });

        viewModel.getRecordFileLiveData().observe(this, s -> {
            filename = s;
            binding.audioPrevContainer.setVisibility(View.VISIBLE);
            viewModel.playAudio(s);
        });

        viewModel.getFileDeletedLiveData().observe(this, aBoolean -> {
            binding.audioPrevContainer.setVisibility(View.GONE);
        });

        viewModel.getPermissionGrantedLiveData().observe(this, aBoolean -> {
            if (!aBoolean) {
                showSettingsDialog();
            }
        });

        viewModel.getIsPlayingLiveData().observe(this, aBoolean -> {
            isPlaying = aBoolean;
            Glide.with(getApplicationContext()).load(aBoolean ? R.drawable.ic_pause : R.drawable.ic_play).into(binding.iconPlay);
            if (aBoolean) {
                handler.postDelayed(mUpdateSeekbar, 1);
            } else {
                handler.removeCallbacks(mUpdateSeekbar);
            }
        });
        viewModel.getTotalDurationLiveData().observe(this, integer -> {
            binding.audioTime.setText(String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(integer) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(integer)), TimeUnit.MILLISECONDS.toSeconds(integer) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(integer))));
        });
        viewModel.getCurrentPositionLiveData().observe(this, integer -> {
            binding.audioProgress.setProgress(integer, false);
        });
        viewModel.getCurrentTimeLiveData().observe(this, integer -> binding.progressTime.setText(String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(integer) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(integer)), TimeUnit.MILLISECONDS.toSeconds(integer) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(integer)))));
        viewModel.getAudioUploadLiveData().observe(this, s -> {
            binding.audioPrevContainer.setVisibility(View.GONE);
            viewModel.uploadAudioToQuestion(SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), kidInfo.getId(), questionId, s);
        });
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(KidsKundliActivity.this);

        builder.setTitle("Need Permissions");

        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
            dialog.cancel();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, 101);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(mUpdateSeekbar);
    }
}