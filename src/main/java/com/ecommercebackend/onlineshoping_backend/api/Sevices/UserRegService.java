package com.ecommercebackend.onlineshoping_backend.api.Sevices;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.cglib.core.Local;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommercebackend.onlineshoping_backend.Exception.EmailFailureException;
import com.ecommercebackend.onlineshoping_backend.Exception.NewUserNotVerifiedException;
import com.ecommercebackend.onlineshoping_backend.Exception.UserAllreadyExistsException;
import com.ecommercebackend.onlineshoping_backend.Models.LocalUser;
import com.ecommercebackend.onlineshoping_backend.Models.VerificationToken;
import com.ecommercebackend.onlineshoping_backend.api.Model.LoginBody;
import com.ecommercebackend.onlineshoping_backend.api.Model.RegistrationBody;
import com.ecommercebackend.onlineshoping_backend.dao.LocalUserDao;
import com.ecommercebackend.onlineshoping_backend.dao.VerificationTokenDAO;

import jakarta.transaction.Transactional;
@Service
public class UserRegService {

  public UserRegService(EncryptionService encryptionService, JwtService jwtService, EmailService emailService,
      VerificationTokenDAO verificationTokenDAO, LocalUserDao localUserDao) {
    this.encryptionService = encryptionService;
    this.jwtService = jwtService;
    this.emailService = emailService;
    this.verificationTokenDAO = verificationTokenDAO;
    this.localUserDao = localUserDao;
  }


  private EncryptionService encryptionService;
  private JwtService jwtService;
  private EmailService emailService;
  private VerificationTokenDAO verificationTokenDAO;

 
  
  

    

    public LocalUserDao localUserDao;

    public LocalUser userRegistration(RegistrationBody registrationBody) throws UserAllreadyExistsException,EmailFailureException{
        if (localUserDao.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
        || localUserDao.findByUsernameIgnoreCase(registrationBody.getUserName()).isPresent()) {
      throw new UserAllreadyExistsException();
    }
      LocalUser user = new LocalUser();
        user.setUsername(registrationBody.getUserName());
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        VerificationToken verificationToken = createVerificationToken(user);
        emailService.sendVerificationEmail(verificationToken);
       return localUserDao.save(user);

    }

    private VerificationToken createVerificationToken(LocalUser user){
      VerificationToken verificationToken =new VerificationToken();
      verificationToken.setToken(jwtService.generateVerificationJwt(user));
      verificationToken.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
      verificationToken.setUser(user);
      user.getVerificationTokens().add(verificationToken);
      return verificationToken;
    }


    public String loginUser(LoginBody login) throws EmailFailureException, NewUserNotVerifiedException{
      Optional<LocalUser> opuser =localUserDao.findByUsernameIgnoreCase(login.getUserName());
      if (opuser.isPresent()) {
        LocalUser user =opuser.get();
        if(EncryptionService.verifyPassword(login.getPassword(),user.getPassword())){
          if (user.isEmailVerified()) {
            return jwtService.generateJWT(user);
          }else{
            List<VerificationToken> verificationTokens = user.getVerificationTokens();
            boolean resend = verificationTokens.size()==0 || verificationTokens.get(0).getCreateTimestamp().before(new Timestamp(System.currentTimeMillis()-(60*60*1000)));
            if(resend){
              VerificationToken verificationToken = createVerificationToken(user);
              verificationTokenDAO.save(verificationToken);
              emailService.sendVerificationEmail(verificationToken);
            }
            throw new NewUserNotVerifiedException(resend);
          }
          
        }
        
      }
      return null;
    }
    @Transactional
    public boolean verifyUser( String token){
      Optional <VerificationToken> optoken = verificationTokenDAO.findByToken(token);
      if (optoken.isPresent()) {
        VerificationToken verificationToken = optoken.get();
        LocalUser user = verificationToken.getUser();
        if (!user.isEmailVerified()) {
          user.setEmailVerified(true);
          localUserDao.save(user);
          verificationTokenDAO.deleteByUser(user);
          return true;
        }
        
      }
      return false;
    }
    
}
