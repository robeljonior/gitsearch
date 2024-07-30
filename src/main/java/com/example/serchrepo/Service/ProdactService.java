package com.example.serchrepo.Service;

import com.example.serchrepo.Model.Bank;
import com.example.serchrepo.Model.ProductType;
import com.example.serchrepo.Model.Products;
import com.example.serchrepo.Model.Userr;
import com.example.serchrepo.Repository.BankRepository;
import com.example.serchrepo.Repository.ProdactTypeRepository;
import com.example.serchrepo.Repository.ProductRepository;
import com.example.serchrepo.Request.ProductRiquest;
import com.example.serchrepo.Response.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdactService {

    private final ProductRepository repository;
    private final ProdactTypeRepository prodactTypeRepository;
    private final BankRepository bankRepository;
    private final GitHubApiClient gitHubApiClient;

    private final RestTemplate restTemplate;

    @Value("{ MyGitHubTokenService }")
    private String githubApiUrl;




    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication == null || !authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails){
            return  ((UserDetails) principal).getUsername();
        }
        String username = principal.toString();
        System.out.println("Username"+username);
        return username;

    }

    public String userToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof Userr){
            return  ((Userr) principal).getGitToken();
        }
        String userToken = principal.toString();

        return userToken;

    }


    public ProductDTO createProduct(ProductRiquest request) throws IOException, URISyntaxException, InterruptedException {
        LocalDateTime dateTime = LocalDateTime.now();

        // Fetch bank and product type by IDs
        Bank bank = bankRepository.findById(request.getBank()).orElse(null);
        ProductType type = prodactTypeRepository.findById(request.getProductType()).orElse(null);


        // Check if product with the given link already exists
        Products existingProduct = repository.findByLink(request.getLink());
        if (existingProduct != null) {
            return null; // Product with the given link already exists
        }
        boolean b = gitHubApiClient.checkReadmeExistence("", "", "");
        System.out.println(b);
        if (gitHubApiClient.checkReadmeExistence(getCurrentUserName(), userToken(), request.getLink())) {
            Products product = new Products();
            product.setBank(bank);
            product.setProductType(type);
            product.setLatestBranch(request.getLatestBranch());
            product.setDescription(request.getDescription());
            product.setLink(request.getLink());
            product.setReadme(b);
            product.setCicd(request.getCicd());
            product.setReceivedDate(dateTime);
            product.setReceivedFrom(getCurrentUserName());
            product.setRepositoryName(request.getRepositoryName());

            Products products=repository.save(product);

//            String command = "rm -rf src/main/resources/gitFile/";
//            Runtime.getRuntime().exec(command);

            return products.productDTO() ;
        }

        return null;

    }

    public List<ProductDTO> getAlls() {
        return repository.findAll()
                .stream().map(Products::productDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> searchProduct(String query) {
        List<Products> products = repository.searchQuery(query);
        return products.stream().map(Products::productDTO).collect(Collectors.toList());
    }

    public ProductDTO vewprodacts(Long id) {
        return repository.findById(id).get().productDTO();
    }

    public Products update(Long id, ProductRiquest request) {

        LocalDateTime dateTime = LocalDateTime.now();

        Bank bank = bankRepository.findById(request.getBank()).orElseThrow(()->new RuntimeException("no bank"));
        ProductType type = prodactTypeRepository.findById(request.getProductType()).orElseThrow(()->new RuntimeException("no product"));
        Products products = repository.findById(id).orElseThrow(null);



           products.setBank(bank);
           products.setProductType(type);
//           products.setLink(request.getLink());
           products.setLatestBranch(request.getLatestBranch());
           products.setDescription(request.getDescription());
           products.setLink(request.getLink());
           products.setReceivedDate(dateTime);
           products.setCicd(request.getCicd());
           products.setReceivedFrom(request.getReceivedFrom());

            return repository.save(products);






    }
}
//        Optional<Userr> userr = userrRepo.findByEmail(getCurrentUserName());
//        System.out.println("User "+userr);
//        String userRepoToken = userr.get().getGitToken();
//
//        String repositoryOwner = getCurrentUserName() ; // Replace with the actual repository owner
//        System.out.println("Repository Owner "+repositoryOwner);
//        String repositoryName = request.getRepositoryName() ; // Replace with the actual repository name
//        String pom_xml =  "/blob/master/pom.xml"; // Replace with the actual JSON file path
////        String userrepoTokenR = userrRepoToken.getGitToken(); //geting token from user
//
//        try {
//            GitHub userGitHub = GitHub.connectUsingOAuth(userRepoToken);
//            GHRepository userRepo = userGitHub.getRepository(repositoryOwner + "/" + repositoryName);
//
//            // Get the content of the JSON file at the specified path
//            GHContent content = userRepo.getFileContent(pom_xml);
//
//            if (content != null) {
//                product.setReadme("yes");
//            } else {
//                product.setReadme("no");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            product.setReadme("error");
//        }

// Save and return product DTO

