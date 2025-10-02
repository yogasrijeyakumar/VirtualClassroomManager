// AdapterPatternDemo.java
interface MediaPlayer { void play(String filename); }

class AudioPlayer implements MediaPlayer {
    public void play(String filename) { System.out.println("Playing audio file: " + filename); }
}

interface AdvancedMediaPlayer { void playVlc(String filename); void playMp4(String filename); }
class VlcPlayer implements AdvancedMediaPlayer {
    public void playVlc(String filename) { System.out.println("Playing VLC file: " + filename); }
    public void playMp4(String filename) {}
}
class Mp4Player implements AdvancedMediaPlayer {
    public void playVlc(String filename) {}
    public void playMp4(String filename) { System.out.println("Playing MP4 file: " + filename); }
}

class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedPlayer;
    public MediaAdapter(String type) {
        if (type.equalsIgnoreCase("vlc")) advancedPlayer = new VlcPlayer();
        else if (type.equalsIgnoreCase("mp4")) advancedPlayer = new Mp4Player();
    }
    public void play(String filename) {
        if (filename.endsWith(".vlc")) advancedPlayer.playVlc(filename);
        else if (filename.endsWith(".mp4")) advancedPlayer.playMp4(filename);
    }
}

public class AdapterPatternDemo {
    public static void main(String[] args) {
        MediaPlayer player = new AudioPlayer();
        player.play("song.mp3");

        MediaPlayer vlcAdapter = new MediaAdapter("vlc");
        vlcAdapter.play("movie.vlc");

        MediaPlayer mp4Adapter = new MediaAdapter("mp4");
        mp4Adapter.play("video.mp4");
    }
}
