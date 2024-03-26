package com.astrocure.astrologer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemAudioLayoutBinding;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class KidsRecordingAdapter extends RecyclerView.Adapter<KidsRecordingAdapter.RecordingViewHolder> {
    Context context;
    List<String> recordingList;
    MediaPlayer mediaPlayer;

    public KidsRecordingAdapter(Context context, List<String> recordingList) {
        this.context = context;
        this.recordingList = recordingList;
        mediaPlayer = new MediaPlayer();
    }

    @NonNull
    @Override
    public RecordingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAudioLayoutBinding binding = ItemAudioLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new RecordingViewHolder(binding);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull RecordingViewHolder holder, int position) {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        holder.binding.playPause.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
            } else {
                try {
                    mediaPlayer.setDataSource(recordingList.get(position));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int totalDuration = mediaPlayer.getDuration();
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    int progressPercent = currentPosition * 100 / totalDuration;
                    holder.binding.audioProgress.setProgress(progressPercent);
                    holder.binding.progressTime.setText(String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(progressPercent) -
                                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(progressPercent)),
                            TimeUnit.MILLISECONDS.toSeconds(progressPercent) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(progressPercent))));
                    handler.postDelayed(this, 10);
                }
            }
        };

        holder.binding.audioProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int newPosition = mediaPlayer.getDuration() * progress / 100;
                    mediaPlayer.seekTo(newPosition);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
            }
        });

        holder.binding.audioTime.setText(String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getDuration()) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mediaPlayer.getDuration())),
                TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.getDuration()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getDuration()))));
    }

    @Override
    public int getItemCount() {
        return recordingList.size();
    }

    public void setRecordings(List<String> newList) {
        final RecordDiffUtil feedDiffUtil = new RecordDiffUtil(this.recordingList, newList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(feedDiffUtil);

        recordingList.clear();
        recordingList.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addRecordings(List<String> newList) {
        recordingList.addAll(newList);
        notifyDataSetChanged();
    }

    public static class RecordingViewHolder extends RecyclerView.ViewHolder {
        ItemAudioLayoutBinding binding;

        public RecordingViewHolder(ItemAudioLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemActionListener {

    }
}

class RecordDiffUtil extends DiffUtil.Callback {
    private final List<String> oldList;
    private final List<String> newList;

    public RecordDiffUtil(List<String> oldList, List<String> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).matches(newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

}

