package io.thebootcode.medium.mediumapi.configs;

import io.thebootcode.medium.mediumapi.models.account.User;
import io.thebootcode.medium.mediumapi.models.enums.AppRole;
import io.thebootcode.medium.mediumapi.models.products.Product;
import io.thebootcode.medium.mediumapi.repositories.ProductRepository;
import io.thebootcode.medium.mediumapi.repositories.UserRepository;
import io.thebootcode.medium.mediumapi.services.UserDetailsServiceImpl;
import io.thebootcode.medium.mediumapi.utils.JwtUtils;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class DefaultDataLoader implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDataLoader.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @SneakyThrows
    public void run(ApplicationArguments args) {
        //----------- test product -------------
        String productName = "My First Product";
        String productUUID = "123";

        if(productRepository.findProductByUuid(productUUID).isEmpty()){
            Product product = new Product();
            product.setName(productName);
            product.setUuid(productUUID);

            productRepository.save(product);
        }

        //----------- default admin user -------------
        String email = "info@thebootcode.io";
        String pswd = "password";

        AtomicReference<User> user = new AtomicReference<>();

        Optional<User> userOptional = userRepository.findByEmail(email);
        userOptional.ifPresentOrElse(user::set, () -> {
                User tempUser = new User();
                tempUser.setEmail(email);
                tempUser.setPassword(encoder.encode(pswd));
                tempUser.setUuid(UUID.randomUUID().toString());
                tempUser.setRoles(new HashSet<>(List.of(AppRole.ROLE_USER, AppRole.ROLE_ADMIN)));

                userRepository.save(tempUser);
                user.set(tempUser);

                logger.info("Default admin: {} (pswd: {}). Please change the password after first login.", tempUser.getEmail(), pswd);
            }
        );

        // FOR TESTING PURPOSE
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
        String jwtToken = jwtUtils.generateJwtToken(authentication);

        logger.info("Current JWT token: {}",jwtToken);
    }
}
