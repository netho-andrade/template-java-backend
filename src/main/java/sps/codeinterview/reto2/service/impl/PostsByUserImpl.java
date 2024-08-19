package sps.codeinterview.reto2.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sps.codeinterview.reto2.model.Posts;
import sps.codeinterview.reto2.service.PostsService;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.springframework.stereotype.Service;

@Service
public class PostsByUserImpl implements PostsService{
  
  private static final String API_GET = "https://jsonplaceholder.typicode.com/posts";
  
  @Override
  public List<Posts> getPostsByUser(int userId){
		
    List<Posts> listPostsReturn = null;
		try(CloseableHttpClient httpClient = HttpClients.createDefault())
		{
			HttpGet httpGet = new HttpGet(API_GET);
			
			try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet))
			{
        System.out.println("RESPONSE::::" + httpResponse.getStatusLine().toString());
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					listPostsReturn = castResponseApi(httpResponse);
          System.out.println();
          System.out.println();
          System.out.println("LISTA RESPONSE::::" + new ObjectMapper().writeValueAsString(listPostsReturn));
				}
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    if (listPostsReturn != null){
      List<Posts> listPostsByUser = listPostsReturn.stream()
                                      .filter(post -> post.getUserId() == userId)
                                      .collect(Collectors.toList());
       try{
         System.out.println();
         System.out.println();
        System.out.println("LISTA FILTRADA::::" + new ObjectMapper().writeValueAsString(listPostsByUser));
      }catch(Exception e){}
      return listPostsByUser;
    }
    else{
       return new ArrayList<>();
    }
  }
  
  private List<Posts> castResponseApi(HttpResponse response) {
		try(InputStream inputStream = response.getEntity().getContent())
		{
			return new ObjectMapper().readValue(inputStream, new TypeReference<List<Posts>>(){});
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
  
}