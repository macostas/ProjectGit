import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.List;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;


public class utilities {
	static String AUTOMATION_PATH = "C:\\results\\";
	
	public static ScreenRecorder getScreenRecorder() throws Exception{

		GraphicsConfiguration gc = GraphicsEnvironment
              .getLocalGraphicsEnvironment()
              .getDefaultScreenDevice()
              .getDefaultConfiguration();

		ScreenRecorder  screenRecorder = new ScreenRecorder(gc,
              new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
              new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                   CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                   DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                   QualityKey, 1.0f,
                   KeyFrameIntervalKey, 15 * 60),
              new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                   FrameRateKey, Rational.valueOf(30)),
              null);

		return screenRecorder;
	}
	
	public static void moveFile(String newName, List<File> createdMovieFiles){
		int part = 0;
		for(File file : createdMovieFiles){
			file.renameTo(new File(AUTOMATION_PATH + newName + part + ".avi"));
			part ++;
		}
	}

	public static void removeFiles(List<File> createdMovieFiles) {
		for(File file : createdMovieFiles){
			file.delete();
		}
	}

}
