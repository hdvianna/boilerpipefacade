/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractor;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import utils.SSLUtil;

/**
 * REST Web Service
 *
 * @author henrique
 */
@Path("article")
public class ArticleResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ArticleResource
     */
    public ArticleResource() {
    }

    /**
     * Retrieves representation of an instance of extractor.ArticleResource
     * @return an instance of java.lang.String
     * @throws java.net.MalformedURLException
     * @throws de.l3s.boilerpipe.BoilerpipeProcessingException
     */
    @GET
    @Produces("text/plain; charset=UTF-8")
    public String getText() throws MalformedURLException, BoilerpipeProcessingException, NoSuchAlgorithmException, KeyManagementException {        
        String params[] = this.context.getRequestUri().getQuery().split("&");
        if (params.length > 0) {
            URL url = new URL(params[0]); // NOTE: Use ArticleExtractor unless DefaultExtractor gives better results for you 
            String result = "";
            try {
                SSLUtil.turnOffSslChecking();
                result = ArticleExtractor.INSTANCE.getText(url);  
            } catch(BoilerpipeProcessingException e)  {
                throw e;
            } catch(NoSuchAlgorithmException e)  {
                throw e;
            } catch(KeyManagementException e)  {
                throw e;
            }finally {
                SSLUtil.turnOnSslChecking();
            }
            return result;
        }
        return "";
    }

    /**
     * PUT method for updating or creating an instance of ArticleResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
}
