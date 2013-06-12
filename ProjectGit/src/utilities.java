import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class utilities {
	static String AUTOMATION_PATH = "C:\\results\\";

	public static ScreenRecorder getScreenRecorder() throws Exception {

		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		ScreenRecorder screenRecorder = new ScreenRecorder(gc, new Format(
				MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
						ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey,
						ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24,
						FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f,
						KeyFrameIntervalKey, 15 * 60), new Format(MediaTypeKey,
						MediaType.VIDEO, EncodingKey, "black", FrameRateKey,
						Rational.valueOf(30)), null);

		return screenRecorder;
	}

	public static void moveFile(String newName, List<File> createdMovieFiles) {
		int part = 0;
		for (File file : createdMovieFiles) {
			file.renameTo(new File(AUTOMATION_PATH + newName + part + ".avi"));
			part++;
		}
	}

	public static void removeFiles(List<File> createdMovieFiles) {
		for (File file : createdMovieFiles) {
			file.delete();
		}
	}

	public static void showErrors(WebDriver driver, String msg) {
		String myscript = "div = document.createElement(\"div\"); "
				+ " div.style.position = \"relative\"; "
				+ " div.style.zindex = \"9999\"; "
				+ " div.style.color = \"blue\"; "
				+ " div.style.fontSize = \"34px\"; "
				+ " div.style.backgroundcolor = \"white\"; "
				+ " div.innerHTML = \""
				+ msg
				+ "\"; 		document.body.insertBefore(div, document.body.firstChild);";
		((JavascriptExecutor) driver).executeScript(myscript);

	}

	public static void startVideoInGrid(String gridServer) {
		String url = "http://" + gridServer
				+ ":4455/RemoteVideoServer/videoServer?action=start";
		try {

			// creating the URL
			URL u = new URL(url);

			// openning a connection
			URLConnection uCon = u.openConnection();
			uCon.connect();
			uCon.getInputStream();
			System.out.println("start video");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void stopVideoInGrid(String gridServer) {
		String url = "http://" + gridServer
				+ ":4455/RemoteVideoServer/videoServer?action=stop";
		try {

			// creating the URL
			URL u = new URL(url);

			// openning a connection
			URLConnection uCon = u.openConnection();
			uCon.connect();
			uCon.getInputStream();
			System.out.println("start video");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveVideoFromGrid(String gridServer, String videoPath) {
		String url = "http://" + gridServer
				+ ":4455/RemoteVideoServer/videoServer?action=stop";
		try {
			URL u = new URL(url);
			URLConnection uCon = u.openConnection();
			uCon.connect();

			InputStream input = uCon.getInputStream();
			byte[] buffer = new byte[4096];
			int n = -1;

			OutputStream output = new FileOutputStream(videoPath);
			while ((n = input.read(buffer)) != -1) {
				if (n > 0) {
					output.write(buffer, 0, n);
				}
			}
			output.close();

			System.out.println("get video");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
