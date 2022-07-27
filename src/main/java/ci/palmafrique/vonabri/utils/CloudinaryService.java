package ci.palmafrique.vonabri.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.ArchiveParams;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

	private static final Logger logger = LoggerFactory.getLogger(CloudinaryService.class);
	@Autowired
	private FileStorageService fileStorageService;
	@Autowired
	private FileStorageProperties fileStorageProperties;
	@Autowired
	private Cloudinary cloudinary;

	public void uploadFile(MultipartFile file, String uuid, String companyName) {
		File uploadedFile = null;
		try {
			uploadedFile = convertMultiPartToFile(file, uuid);
			cloudinary.uploader().upload(uploadedFile, ObjectUtils.asMap("public_id",
					"SoutraJob/Profile-Pics/" + companyName + "/" + uuid, "tags", companyName));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			uploadedFile.delete();
		}
	}
	public void uploadFileSimple(MultipartFile file,String name) {
		File uploadedFile = null;
		try {
			//https://res.cloudinary.com/palmafrique/image/upload/v1658496799/cld-sample-2.jpg
			uploadedFile = convertMultiPartToFile(file);
			//cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
			cloudinary.uploader().upload(uploadedFile, ObjectUtils.asMap("public_id",
					"palmafrique/Profile-Pics/" + name + "/", "tags", name));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			uploadedFile.delete();
		}
	}

    public Map uploadMultipartFile(MultipartFile multipartFile,String name) throws IOException {
        //File file = convert(multipartFile);
        
		String fileName = fileStorageService.storeFileWithName(multipartFile,name);
		Path pathDest = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		String pathDestNormalize = pathDest.toString();
		pathDestNormalize += "/" + fileName;
		String file_name_full = pathDestNormalize;
        System.out.println("result =====>: " +  file_name_full);
        Map result = cloudinary.uploader().upload(file_name_full, ObjectUtils.asMap("use_filename", "true","unique_filename", "false"));
       // multipartFile.delete();
        System.out.println("result =====>: " + result);
        return result;
    }

    public Map uploadFile(File file) throws IOException {
        Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap("use_filename", "true","unique_filename", "false","resource_type", "auto"));
        file.delete();
        System.out.println("result =====>: " + result);
        return result;
    }
    public Map delete(String id) throws IOException {
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }

	public boolean uploadBase64IMAGE(String base64,String name) {
		boolean isUploaded = false;
		String uploadedFile = null;
		try {
			//https://res.cloudinary.com/palmafrique/image/upload/v1658496799/cld-sample-2.jpg
			
			uploadedFile = "data:image/jpg;"+base64;
			cloudinary.uploader().upload(uploadedFile,
					  ObjectUtils.emptyMap());
			//cloudinary.uploader().upload(uploadedFile, ObjectUtils.asMap("public_id","palmafrique/Profile-Pics/" + name + "/", "tags", name));
			isUploaded = true;
		} catch (IOException e) {
			isUploaded = false;
			logger.error(e.getMessage(), e);
		} finally {
			//uploadedFile.delete();
		}
       return isUploaded;
	}
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
	public String getFileUrl(String uuid, String companyName) {
		return "https://res.cloudinary.com/hzpfvsayq/image/upload/q_auto:low/SoutraJob/Profile-Pics/" + companyName
				+ "/" + uuid;
	}
	public String getPhotoUrl(String name) {
		return "https://res.cloudinary.com/palmafrique/image/upload/" + name;
	}
	@Async
	public void deletePhotoFolder(String code) {
		try {
			cloudinary.api().deleteResourcesByTag("SoutraJob/Profile-Pics/" + code, ObjectUtils.emptyMap());
			cloudinary.api().deleteFolder("SoutraJob/Profile-Pics/" + code, ObjectUtils.emptyMap());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Async
	public void deleteSinglePhoto(String filename) {
		try {
			cloudinary.uploader().destroy(filename, ObjectUtils.emptyMap());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Async
	public void deleteMultPhoto(String[] filenames) {
		try {
			cloudinary.api().deleteResources(Arrays.asList(filenames), ObjectUtils.emptyMap());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public String urlDownload(String code) throws UnsupportedEncodingException {
		String tag = "SoutraJob/Profile-Pic/" + code;
		return cloudinary.downloadArchive(new ArchiveParams().tags(new String[] { tag })
				.targetTags(new String[] { tag }).targetPublicId(code).flattenFolders(true));
	}

	public File convertMultiPartToFile(MultipartFile file, String uuid) throws IOException {
		File convFile = File.createTempFile(uuid, null);
		try (FileOutputStream fos = new FileOutputStream(convFile)) {
			fos.write(file.getBytes());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return convFile;
	}

}