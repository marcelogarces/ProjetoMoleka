package br.com.moleka.servico;

import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class DirecaoService {

	public static void main(String[] args) {

		String start = "nilópolis";
		String finish = "nova iguacu";

		String region = "rj";

		System.out.println(calculateRoute(start, finish, region));
	}

	private static String calculateRoute(String start, String finish,
			String region) {
		String result = "";
		String urlString = "http://maps.googleapis.com/maps/api/directions/json?sensor=false&origin="
				+ start + "&destination=" + finish;
		System.out.println(urlString);

		try {
			URL urlGoogleDirService = new URL(urlString);

			HttpURLConnection urlGoogleDirCon = (HttpURLConnection) urlGoogleDirService
					.openConnection();

			urlGoogleDirCon.setAllowUserInteraction(false);
			urlGoogleDirCon.setDoInput(true);
			urlGoogleDirCon.setDoOutput(false);
			urlGoogleDirCon.setUseCaches(true);
			urlGoogleDirCon.setRequestMethod("GET");
			urlGoogleDirCon.connect();

			DocumentBuilderFactory factoryDir = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder parserDirInfo = factoryDir.newDocumentBuilder();
			Document docDir = parserDirInfo.parse(urlGoogleDirCon
					.getInputStream());

			urlGoogleDirCon.disconnect();
			result = docDir.toString();

			return result;
		}

		catch (Exception e) {
			System.out.println(e);
			return null;
		}

	};

}
