package org.streampipes.manager.storage;

import org.streampipes.model.client.user.RegistrationData;
import org.streampipes.model.client.user.Role;
import org.streampipes.model.client.user.User;
import org.streampipes.storage.management.StorageDispatcher;
import org.streampipes.user.management.util.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

public class UserManagementService {

  public Boolean registerUser(RegistrationData data, Set<Role> roles) {
    org.streampipes.model.client.user.User user = new User(data.getEmail(), data.getPassword(), roles);

    try {
      String encryptedPassword = PasswordUtil.encryptPassword(data.getPassword());
      user.setPassword(encryptedPassword);
      StorageDispatcher.INSTANCE.getNoSqlStore().getUserStorageAPI().storeUser(user);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      return false;
    }

    return true;
  }

  public static UserService getUserService() {
    return new UserService(StorageDispatcher.INSTANCE.getNoSqlStore().getUserStorageAPI());
  }

}
