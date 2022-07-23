
/*
 * Created on 2022-04-25 ( Time 03:19:02 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import ci.palmafrique.vonabri.utils.dto.customize._FileDto;

/**
 * Utilities
 * 
 * @author Geo
 *
 */
public class Utilities {

	public static Date getCurrentDate() {
		return new Date();
	}

	public static boolean areEquals(Object obj1, Object obj2) {
		return (obj1 == null ? obj2 == null : obj1.equals(obj2));
	}

	public static <T extends Comparable<T>, Object> boolean areEquals(T obj1, T obj2) {
		return (obj1 == null ? obj2 == null : obj1.equals(obj2));
	}

	private static final String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP",
			"HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		for (String header : IP_HEADER_CANDIDATES) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}
	public static <T> List<T> paginner(List<T> allItems, Integer index, Integer size) {
		if (isEmpty(allItems)) {
			return null;
		}

		List<T> items = new ArrayList<T>();
		// si une pagination est pécisée, ne prendre que les éléments demandés
		if (index != null && size != null) {
			Integer fromIndex = index * size;
			if (fromIndex < allItems.size()) {
				Integer toIndex = fromIndex + size;
				if (toIndex > allItems.size())
					toIndex = allItems.size();
				items.addAll(allItems.subList(fromIndex, toIndex));
			}
		} else {
			items.addAll(allItems);
		}

		return items;
	}
//	public static User getSupers(String superMail) {
//
//		List<User> devs = new ArrayList<User>();
//		User user1 = new User();
//		user1.setEmail("lefa@gmail.com");
//		user1.setPassword("azerty1234");
//		devs.add(user1);
//		
//		User user2 = new User();
//		user2.setEmail("brice@gmail.com");
//		user2.setPassword("azerty1234");
//		devs.add(user2);
//		User retour = null;
//		for (User sup : devs) {
//			if(sup.getEmail().equals(superMail)) {
//				retour = sup;
//			}
//		}
//		
//		return retour;
//	}
	public static <T> boolean isEmpty(List<T> list) {
		return (list == null || list.isEmpty());
	}
	private static List<String> listeBase = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1",
			"2", "3", "4", "5", "6", "7", "8", "9");

	public static String combinaisonString() {
		String lettres = "";
		try {
			Random random;
			for (int i = 0; i < 10; i++) {
				random = new Random();
				int rn = random.nextInt(35 - 0 + 1) + 0;
				lettres += listeBase.get(rn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lettres;
	}

	public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {
		if (!notBlank(date)) {
			return "";
		}
		Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
		String parsedDate = formatter.format(initDate);

		return parsedDate;
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static int duration(Date startDate, Date endDate) {
		long duration = ChronoUnit.DAYS.between(asLocalDate(startDate), asLocalDate(endDate));
		return Integer.parseInt(String.valueOf(duration + 1));
	}

	public static int duration(LocalDate startLocalDate, LocalDate endLocalDate) {
		long duration = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
		return Integer.parseInt(String.valueOf(duration + 1));
	}
	
	/**
	 * Check if a String given is an Integer.
	 *
	 * @param s
	 * @return isValidInteger
	 *
	 */
	public static boolean isInteger(String s) {
		boolean isValidInteger = false;
		try {
			Integer.parseInt(s);

			// s is a valid integer
			isValidInteger = true;
		} catch (NumberFormatException ex) {
			// s is not an integer
		}

		return isValidInteger;
	}

	public static String generateCodeOld() {
		String formatted = null;
		formatted = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
		return formatted;
	}

	public static String generateAlphabeticCode(Integer nbreCaractere) {
		String formatted = null;
		formatted = RandomStringUtils.randomAlphabetic(nbreCaractere);
		return formatted;
	}
	public static String generateCode() {
		String formatted = null;
		SecureRandom secureRandom = new SecureRandom();
		int num = secureRandom.nextInt(100000000);
		formatted = String.format("%05d", num);
		return formatted;
	}

	public static boolean isTrue(Boolean b) {
		return b != null && b;
	}

	public static boolean isFalse(Boolean b) {
		return !isTrue(b);
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Long.parseLong(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Check if a Integer given is an String.
	 *
	 * @param i
	 * @return isValidString
	 *
	 */
	public static boolean isString(Integer i) {
		boolean isValidString = true;
		try {
			Integer.parseInt(i + "");

			// i is a valid integer

			isValidString = false;
		} catch (NumberFormatException ex) {
			// i is not an integer
		}

		return isValidString;
	}

	public static boolean isValidEmail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public static String encrypt(String str) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] hashedBytes = digest.digest(str.getBytes("UTF-8"));

		return convertByteArrayToHexString(hashedBytes);
	}

	public static boolean isDateValid(String date) {
		try {
			String simpleDateFormat = "dd/MM/yyyy";

			if (date.contains("-"))
				simpleDateFormat = "dd-MM-yyyy";
			else if (date.contains("/"))
				simpleDateFormat = "dd/MM/yyyy";
			else
				return false;

			DateFormat df = new SimpleDateFormat(simpleDateFormat);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static String GenerateValueKey(String code) {
		String result = null;
		// String prefix = prefixe;
		String suffix = null;
		String middle = null;
		String separator = "-";
		final String defaut = "000001";
		try {

			SimpleDateFormat dt = new SimpleDateFormat("yy-MM-dd-ss");
			String _date = dt.format(new Date());
			String[] spltter = _date.split(separator);
			middle = spltter[0] + spltter[1] + spltter[2] + spltter[3];
			if (code != null) {
				// Splitter le code pour recuperer les parties
				// String[] parts = code(separator);
				String part = code.substring(1);
				System.out.println("part" + part);

				if (part != null) {
					int cpt = new Integer(part);
					cpt++;

					String _nn = String.valueOf(cpt);

					switch (_nn.length()) {
						case 1:
							suffix = "00000" + _nn;
							break;
						case 2:
							suffix = "0000" + _nn;
							break;
						case 3:
							suffix = "000" + _nn;
							break;
						case 4:
							suffix = "00" + _nn;
							break;
						case 5:
							suffix = "0" + _nn;
							break;
						default:
							suffix = _nn;
							break;
					}
					// result = prefix + separator + middle + separator +
					// suffix;
					result = middle + separator + suffix;
				}
			} else {
				// result = prefix + separator + middle + separator + defaut;
				result = middle + separator + defaut;
			}
		} catch (Exception e) {

		}
		return result;
	}

	public static Integer getAge(Date dateNaissance) throws ParseException, Exception {
		Integer annee = 0;

		if (dateNaissance == null) {
			annee = 0;
		}
		Calendar birth = new GregorianCalendar();
		birth.setTime(dateNaissance);
		Calendar now = new GregorianCalendar();
		now.setTime(new Date());
		int adjust = 0;
		if (now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR) < 0) {
			adjust = -1;
		}
		annee = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + adjust;
		return annee;
	}

	public static Boolean AvailableCode(String code) {
		if (code == null || code.isEmpty()) {
			return false;
		}
		Locale local = new Locale(code, "");
		return LocaleUtils.isAvailableLocale(local);

	}

	public static String normalizeFileName(String fileName) {
		String fileNormalize = null;
		fileNormalize = fileName.trim().replaceAll("\\s+", "_");
		fileNormalize = fileNormalize.replace("'", "");
		fileNormalize = Normalizer.normalize(fileNormalize, Normalizer.Form.NFD);
		fileNormalize = fileNormalize.replaceAll("[^\\p{ASCII}]", "");

		return fileNormalize;
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

	public static SimpleDateFormat findDateFormat(String date) {
		SimpleDateFormat simpleDateFormat = null;
		String regex_dd_MM_yyyy = "\\A0?(?:3[01]|[12][0-9]|[1-9])[/.-]0?(?:1[0-2]|[1-9])[/.-][0-9]{4}\\z";

		if (date.matches(regex_dd_MM_yyyy))
			if (date.contains("-"))
				simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			else if (date.contains("/"))
				simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		return simpleDateFormat;
	}

	/**
	 * @return Permet de retourner la date courante du système
	 *
	 */
	public static String getCurrentLocalDateTimeStamp() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	/**
	 * @param l
	 *            liste de vérification de doublons
	 * @return retourne le nombre de doublon trouvé
	 *
	 */
	public static int getDupCount(List<String> l) {
		int cnt = 0;
		HashSet<String> h = new HashSet<>(l);

		for (String token : h) {
			if (Collections.frequency(l, token) > 1)
				cnt++;
		}

		return cnt;
	}

	public static boolean saveImage(String base64String, String nomCompletImage, String extension) throws Exception {

		BufferedImage image = decodeToImage(base64String);

		if (image == null) {

			return false;

		}

		File f = new File(nomCompletImage);

		// write the image

		ImageIO.write(image, extension, f);

		return true;

	}
	public static MultipartFile convertBase64ToMultipartFile(String base64String, String nomCompletImage, String extension) throws Exception {

		BufferedImage image = decodeToImage(base64String);

		if (image == null) {

			return null;

		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( image, extension, baos );
		
		baos.flush();
		System.out.println("BufferedImage SIZE=====>"+baos.size());
		MultipartFile multipartFile = new MultipartImage(baos.toByteArray());
		// write the image
		return multipartFile;
	}
	
	
	
	public static boolean saveVideo(String base64String, String nomCompletVideo) throws Exception {

		try {

			byte[] decodedBytes = Base64.getDecoder().decode(base64String);
			File file2 = new File(nomCompletVideo);
			FileOutputStream os = new FileOutputStream(file2, true);
			os.write(decodedBytes);
			os.close();

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

		return true;

	}

	public static BufferedImage decodeToImage(String imageString) throws Exception {

		BufferedImage image = null;

		byte[] imageByte;

		imageByte = Base64.getDecoder().decode(imageString);

		try (ByteArrayInputStream bis = new ByteArrayInputStream(imageByte)) {

			image = ImageIO.read(bis);

		}

		return image;

	}

	public static String encodeToString(BufferedImage image, String type) {

		String imageString = null;

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {

			ImageIO.write(image, type, bos);

			byte[] imageBytes = bos.toByteArray();

			imageString = new String(Base64.getEncoder().encode(imageBytes));

			bos.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return imageString;

	}

	public static String convertFileToBase64(String pathFichier) {
		File originalFile = new File(pathFichier);
		String encodedBase64 = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
			byte[] bytes = new byte[(int) originalFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.getEncoder().encodeToString((bytes)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return encodedBase64;
	}

	public static String getImageExtension(String str) {
		String extension = "";
		int i = str.lastIndexOf('.');
		if (i >= 0) {
			extension = str.substring(i + 1);
			return extension;
		}
		return null;
	}

	public static boolean fileIsImage(String image) {

		String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|jpeg))$)";
		Pattern pattern = Pattern.compile(IMAGE_PATTERN);
		Matcher matcher = pattern.matcher(image);

		return matcher.matches();

	}

	public static boolean fileIsVideo(String video) {

		String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(mp4|avi|camv|dvx|mpeg|mpg|wmv|3gp|mkv))$)";
		Pattern pattern = Pattern.compile(IMAGE_PATTERN);
		Matcher matcher = pattern.matcher(video);

		return matcher.matches();

	}

	public static void createDirectory(String chemin) {
		File file = new File(chemin);
		if (!file.exists()) {
			try {
				FileUtils.forceMkdir(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void deleteFolder(String chemin) {
		File file = new File(chemin);
		try {
			if (file.exists() && file.isDirectory()) {
				FileUtils.forceDelete(new File(chemin));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteFile(String chemin) {
		File file = new File(chemin);
		try {
			if (file.exists() && file.getName() != null && !file.getName().isEmpty()) {

				FileUtils.forceDelete(new File(chemin));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean notBlank(String str) {
		return str != null && !str.isEmpty() && !str.equals("\n");
	}
	
	public static boolean notEmpty(List<String> lst) {
		return lst != null && !lst.isEmpty() && lst.stream().noneMatch(s -> s.equals("\n")) && lst.stream().noneMatch(s -> s.equals(null));
	}
	
	public static <T> boolean isNotEmpty(List<T> list){
		return (list != null && !list.isEmpty());
	}

	static public String GetCode(String Value, Map<String, String> Table) {

		for (Entry<String, String> entry : Table.entrySet()) {
			if (entry.getValue().equals(Value)) {
				return entry.getKey();
			}
		}
		return Value;
	}

	public static boolean anObjectFieldsMapAllFieldsToVerify(List<Object> objets, Map<String, Object> fieldsToVerify) {
		for (Object objet : objets) {
			boolean oneObjectMapAllFields = true;
			JSONObject jsonObject = new JSONObject(objet);
			for (Map.Entry<String, Object> entry : fieldsToVerify.entrySet()) {
				// slf4jLogger.info("jsonObject " +jsonObject);
				String key = entry.getKey();
				Object value = entry.getValue();
				try {
					if (!jsonObject.get(key).equals(value)) {
						oneObjectMapAllFields = false;
						break;
					}
				} catch (Exception e) {
					oneObjectMapAllFields = false;
					break;
				}
			}
			if (oneObjectMapAllFields)
				return true;
		}

		return false;
	}

	  @SuppressWarnings("unused")
	  public static String encryptWalletKeyString(String token) {
	              String secretKey = "vonabri";
	              String dataToEncrypt = token;
		        try {
		            byte[] key = secretKey.getBytes("UTF-8");
		            MessageDigest sha = MessageDigest.getInstance("SHA-1");
		            key = sha.digest(key);
		            key = Arrays.copyOf(key, 16);
		            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
		            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		            byte[] encryptedData = cipher.doFinal(dataToEncrypt.getBytes("UTF-8"));
		            String encryptedValue = Base64.getEncoder().encodeToString(encryptedData);
					System.out.println("WALLET QRCODE ENCRYPT===>"+ encryptedValue);
		            return encryptedValue;
		        } catch (UnsupportedEncodingException ex) {
		            ex.printStackTrace();
		        } catch (NoSuchAlgorithmException ex) {
		            ex.printStackTrace();
		        } catch (NoSuchPaddingException ex) {
		            ex.printStackTrace();
		        } catch (InvalidKeyException ex) {
		            ex.printStackTrace();
		        } catch (BadPaddingException ex) {
		            ex.printStackTrace();
		        } catch (IllegalBlockSizeException ex) {
		            ex.printStackTrace();
		        }
		        return null;
		}


  @SuppressWarnings("unused")
	public static String decryptWalletKeyString(String qrcode) {
       String secretKey = "vonabri";
		String walletKey = qrcode;
		System.out.println("WALLET STRING KEY TO DECRYPT===>"+walletKey);
      try {
          byte[] key = secretKey.getBytes("UTF-8");
          MessageDigest sha = MessageDigest.getInstance("SHA-1");
          key = sha.digest(key);
          key = Arrays.copyOf(key, 16);
          SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
          Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
          cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
          byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(walletKey));
          String decryptedValue = new String(decryptedData);
			System.out.println("WALLET ID AFTER DECRYPT===>"+decryptedValue);

          return decryptedValue ;
      } catch (UnsupportedEncodingException ex) {
          ex.printStackTrace();
      } catch (NoSuchAlgorithmException ex) {
          ex.printStackTrace();
      } catch (NoSuchPaddingException ex) {
          ex.printStackTrace();
      } catch (InvalidKeyException ex) {
          ex.printStackTrace();
      } catch (BadPaddingException ex) {
          ex.printStackTrace();
      } catch (IllegalBlockSizeException ex) {
          ex.printStackTrace();
      }
      return null;
  }
	public static String generateAlphanumericCode(Integer nbreCaractere) {
		String formatted = null;
		formatted = RandomStringUtils.randomAlphanumeric(nbreCaractere).toUpperCase();
		return formatted;
	}

	public static Boolean verifierEmail(String email) {
		Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher emailMatcher = emailPattern.matcher(email);
		return emailMatcher.matches();
	}
	public static boolean isCellEmpty(final Cell cell) {
	    if (cell == null) { // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) to avoid null cells
	        return true;
	    }

	    if (cell.getCellType() == CellType.BLANK) {
	        return true;
	    }

	    if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty()) {
	        return true;
	    }

	    return false;
	}
	
	public static boolean fileIsTexteDocument(String textDocument) {

		String TEXT_DOCUMENT_PATTERN = "([^\\s]+(\\.(?i)(doc|docx|txt|odt|ods|pdf|xls|xlsx))$)";
		Pattern pattern = Pattern.compile(TEXT_DOCUMENT_PATTERN);
		Matcher matcher = pattern.matcher(textDocument);
		return matcher.matches();
	}
	public static String getSuitableFileDirectory(String fileExtension, ParamsUtils paramsUtils) {
		String suitableFileDirectory = null;
		if (fileIsImage("file." + fileExtension)) {
			suitableFileDirectory = paramsUtils.getImageDirectory();
		} else {
			if (fileIsTexteDocument("file." + fileExtension)) {
				suitableFileDirectory = paramsUtils.getTextfileDirectory();
			} else {
				if (fileIsVideo("file." + fileExtension)) {
					suitableFileDirectory = paramsUtils.getVideoDirectory();
				}
			}
		}
		if (suitableFileDirectory == null) {
			suitableFileDirectory = paramsUtils.getOtherfileDirectory();
		}
		return String.format("%s%s", paramsUtils.getRootFilesPath(), suitableFileDirectory);
	}
	
	public static boolean saveFile(String base64String, String nomCompletFichier) throws Exception {
		System.out.println("DANS SAVEFILE");
	try {

		byte[] decodedBytes = Base64.getDecoder().decode(base64String);
		File file2 = new File(nomCompletFichier);
		FileOutputStream os = new FileOutputStream(file2, true);
		os.write(decodedBytes);
		os.close();

	} catch (Exception e) {
		// TODO: handle exception
		return false;
	}

	return true;

}
	
public static boolean saveFileBASE64(String base64String, String nomCompletFichier) throws Exception {
		System.out.println("DANS SAVEFILE");
	try {

		byte[] decodedBytes = Base64.getDecoder().decode(base64String);
		File file2 = new File(nomCompletFichier);
		FileOutputStream os = new FileOutputStream(file2, true);
		os.write(decodedBytes);
		os.close();

	} catch (Exception e) {
		// TODO: handle exception
		return false;
	}

	return true;

}
	public static String getSuitableFileUrl(String fileName, ParamsUtils paramsUtils) {
		String suitableFileDirectory = null;
		if(fileName.contains(".")) {
		String file[] = fileName.split("\\.");
		int i = 0, j = 1;
		if (file.length > 0) {
			String fileExtension = (file.length > 2) ? file[(file.length - 1)] : file[j];
			if (fileIsImage("file." + fileExtension)) {
				suitableFileDirectory = paramsUtils.getImageDirectory();
			} else {
				if (fileIsTexteDocument("file." + fileExtension)) {
					suitableFileDirectory = paramsUtils.getTextfileDirectory();
				} else {
					if (fileIsVideo("file." + fileExtension)) {
						suitableFileDirectory = paramsUtils.getVideoDirectory();
					}
				}
			}
		}
	}
		if (suitableFileDirectory == null) {
			suitableFileDirectory = paramsUtils.getOtherfileDirectory();
		}
		return String.format("%s%s%s", paramsUtils.getUrlRoot(), suitableFileDirectory, fileName);
	}
	
	
	public static String saveFile(_FileDto dataFile, ParamsUtils paramsUtils) throws IOException, Exception {
		String filePath = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss_SSSSS");
		String fileName = null;
		if (dataFile != null) {
			String fileDirectory = null;
			if (notBlank(dataFile.getFileName()) && notBlank(dataFile.getFileBase64())
					&& notBlank(dataFile.getExtension())) {
				fileName = dataFile.getFileName();
				if (fileName.contains("/")) {
					String[] fileNames = dataFile.getFileName().split("/");
					fileName = fileNames[fileNames.length - 1];
				}
				fileName = normalizeFileName(fileName) + "_" + sdf.format(new Date()) + "." + dataFile.getExtension();

				// S'assurer que l'extension est bonne
				if (!fileIsImage(fileName) && !fileIsTexteDocument(fileName) && !fileIsVideo(fileName)) {
					System.out.println(
							"\n\nL'extension '" + dataFile.getExtension() + "' n'est pas prise en compte !\n\n");
					return null;
				}

				// Repertoire où je depose mon fichier
				// String filesDirectory = paramsUtils.getImageDirectory();
				String filesDirectory = getSuitableFileDirectory(dataFile.getExtension(), paramsUtils);
				String filesD = filesDirectory;
				System.out.println("filesDirectoryIEW V " + filesDirectory);
				createDirectory(filesDirectory);
				if (!filesDirectory.endsWith("/")) {
					filesDirectory += "/";
				}
				fileDirectory = filesDirectory + fileName;

				// Enregistrement du fichier
				boolean succes = false;
				succes = Utilities.saveFile(dataFile.getFileBase64(), fileDirectory);
				if (!succes) {
					System.out.println("\n\nEchec de l'enregistrement du fichier '" + fileDirectory + "' !\n\n");
					return null;
				}
				filePath = fileDirectory;

				//Reduction
//				boolean reduction = Utilities.saveScaledImage(fileDirectory,filesD,fileName);
//				if (!reduction) {
//					System.out.println("\n\nEchec de la reduction du fichier '" + fileDirectory + "' !\n\n");
//				}else {
//					System.out.println("\n\nRéduction du fichier réussie '" + fileDirectory + "' !\n\n");
//				}
			}
		}

		return fileName;
	}
}
