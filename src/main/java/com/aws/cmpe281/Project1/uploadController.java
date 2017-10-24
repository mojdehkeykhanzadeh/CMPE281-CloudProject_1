package com.aws.cmpe281.Project1;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
@Controller
public class uploadController extends S3Config {
	private String bucketName = "hw2-281bucket";
	private String name = null;
	@Autowired
	private userFileDAO userFileDAO;
	@Autowired
	private AmazonS3 s3Client;
	public static final int MAX_FILE_SIZE = 1024 * 1024 * 10; 
	@RequestMapping(method=RequestMethod.POST, value="/upload")
	public @ResponseBody String fileUpload_downlaod( @RequestParam("file") MultipartFile file){
		if(file.getSize() > MAX_FILE_SIZE)
	    {
	        throw new IllegalArgumentException(
	                "File is to big! Max size is " + MAX_FILE_SIZE);
	    } 
		try{
			//get input
			InputStream is =file.getInputStream();
			//
			name =file.getOriginalFilename();
			//save in S3 with read access
			s3Client.putObject(new PutObjectRequest(bucketName,name,is,new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead));
			S3Object s3obj = s3Client.getObject(new GetObjectRequest(bucketName,name));
			System.out.println(s3obj.getObjectContent().getHttpRequest().toString());
			
			URL url = s3Client.getUrl(bucketName, name);
			ObjectMapper mapper = new ObjectMapper();
			 ObjectNode objectNode1 = mapper.createObjectNode();
		        objectNode1.put("name", name );
		   objectNode1.put("url", url.toString()); 
			
			return  objectNode1.toString();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return  null;
	}	
	
	@RequestMapping(method=RequestMethod.GET, value="/files")
	public @ResponseBody List<S3ObjectSummary> retirieveFiles(@RequestParam String name) throws IOException{
		//String name = "";
		ObjectListing listing = s3Client.listObjects( bucketName, name );
		List<S3ObjectSummary> s3ObjectSummaries = listing.getObjectSummaries();
		
		return s3Client.listObjects(bucketName, name).getObjectSummaries();
		}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/delete")
	public @ResponseBody  void  deleteFiles(@RequestParam String name) throws IOException{
		s3Client.deleteObject(bucketName, name);
		System.out.println("here me +++"+name);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/config")
	public @ResponseBody  void  configRds() throws IOException{
		System.out.println("configuring data source"+name);
		userFileDAO.setdataSource();
		
		
	}
	}
