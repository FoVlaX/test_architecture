package com.example.pagerlistapp


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.*
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.IOException
import kotlin.experimental.or
import kotlin.experimental.xor
import kotlin.random.Random


private const val LOG_TAG = "AudioRecordTest"
private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

class InfoActivity : AppCompatActivity() {

    private var fileName: String = ""

    private var recordButton: RecordButton? = null
    private var recorder: MediaRecorder? = null

    private var playButton: PlayButton? = null
    private var player: MediaPlayer? = null

    // Requesting permission to RECORD_AUDIO
    private var permissionToRecordAccepted = false
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
        if (!permissionToRecordAccepted) finish()
    }

    private fun onRecord(start: Boolean) = if (start) {
        startRecording()
    } else {
        stopRecording()
    }

    private fun onPlay(start: Boolean) = if (start) {
        startPlaying()
    } else {
        stopPlaying()
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }

    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }



    class Record : Thread() {

        val sampleRateInHz = 44100
        val RecordBufferSize=AudioRecord.getMinBufferSize(sampleRateInHz,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT);
        val TrackBufferSize= AudioTrack.getMinBufferSize(sampleRateInHz,AudioFormat.CHANNEL_OUT_MONO,AudioFormat.ENCODING_PCM_16BIT);

        var isRecording = false
        val buffer = ShortArray(RecordBufferSize)
        var readBuffer = ShortArray(TrackBufferSize)
        override fun run() {

            isRecording = true
            Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_AUDIO)

            val arec = AudioRecord(MediaRecorder.AudioSource.MIC, sampleRateInHz, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, RecordBufferSize)
            val atrack = AudioTrack(AudioManager.STREAM_MUSIC, sampleRateInHz, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, TrackBufferSize, AudioTrack.MODE_STREAM)
            //am.setRouting(AudioManager.MODE_NORMAL, AudioManager.ROUTE_EARPIECE, AudioManager.ROUTE_ALL);

            atrack.playbackRate = sampleRateInHz
            val buffer = ByteArray(RecordBufferSize)
            arec.startRecording()
            atrack.play()

            while (isRecording) {
                val audioLenght = arec.read(buffer, 0, RecordBufferSize)
                atrack.write(buffer, 0, audioLenght)
            }

            arec.stop()
            atrack.stop()
            isRecording = false
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    internal inner class RecordButton(ctx: Context) : androidx.appcompat.widget.AppCompatButton(ctx) {

        var mStartRecording = true

        var clicker: OnClickListener = OnClickListener {
            onRecord(mStartRecording)
            text = when (mStartRecording) {
                true -> "Stop recording"
                false -> "Start recording"
            }
            mStartRecording = !mStartRecording
        }

        init {
            text = "Start recording"
            setOnClickListener(clicker)
        }
    }

    internal inner class PlayButton(ctx: Context) : androidx.appcompat.widget.AppCompatButton(ctx) {
        var mStartPlaying = true
        var clicker: OnClickListener = OnClickListener {
            onPlay(mStartPlaying)
            text = when (mStartPlaying) {
                true -> "Stop playing"
                false -> "Start playing"
            }
            mStartPlaying = !mStartPlaying
        }

        init {
            text = "Start playing"
            setOnClickListener(clicker)
        }
    }

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        // Record to the external cache directory for visibility
        fileName = "${externalCacheDir?.absolutePath}/audiorecordtest.3gp"

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION)

        Record().start()

        recordButton = RecordButton(this)
        playButton = PlayButton(this)
        val ll = LinearLayout(this).apply {
            addView(recordButton,
                    LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            0f))
            addView(playButton,
                    LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            0f))
        }
        setContentView(ll)
    }

    override fun onStop() {
        super.onStop()
        recorder?.release()
        recorder = null
        player?.release()
        player = null
    }
}