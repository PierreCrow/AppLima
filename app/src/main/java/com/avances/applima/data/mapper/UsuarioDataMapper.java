package com.avances.applima.data.mapper;

import android.content.Context;

import com.avances.applima.data.datasource.cloud.model.security.response.WsDataUser;
import com.avances.applima.data.datasource.cloud.model.security.response.WsDataUserLoginSocialMedia;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.domain.model.Usuario;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;


public class UsuarioDataMapper {

    Context context;

    public UsuarioDataMapper() {
      //  this.context=context;
    }


    public Usuario transformToEntity(WsDataUser wsDataUser) {
        Usuario usuario = new Usuario(wsDataUser.getId(),
                wsDataUser.getName(),
                wsDataUser.getBirthDate(),
                wsDataUser.getSex(),
                wsDataUser.getCountry(),
                wsDataUser.getEmail(),
                wsDataUser.getRegisterType(),
                wsDataUser.getImage(),
                wsDataUser.getRegisterState());
        return usuario;
    }



    public Usuario transformFromSocialMediaToEntity(WsDataUserLoginSocialMedia wsDataUser) {
        Usuario usuario = new Usuario(wsDataUser.getId(),
                wsDataUser.getName(),
                wsDataUser.getBirthDate(),
                wsDataUser.getSex(),
                wsDataUser.getCountry(),
                wsDataUser.getEmail(),
                wsDataUser.getRegisterType(),
                wsDataUser.getImage(),
                wsDataUser.getRegisterState());
        return usuario;
    }


    public UserPreference transformToPreference(Usuario usuario) {
        UserPreference userPreference = Helper.getUserAppPreference(context);
        userPreference.setEmail(usuario.getEmail());
        userPreference.setCountry(usuario.getCountry());
        userPreference.setImage(usuario.getImage());

    /*    UserPreference userPreference =
                new UserPreference(usuario.getName(),
                        "",
                        usuario.getEmail(),
                        "",
                        "",
                        usuario.getCountry(),
                        usuario.getImage(),
                        Constants.LOGIN_TYPES.NOT_LOGIN,
                        false,
                        false,
                        false,
                        Constants.INTERESTS.NO_INTEREST,
                        Constants.INTERESTS.NO_INTEREST,
                        Constants.INTERESTS.NO_INTEREST,
                        Constants.INTERESTS.NO_INTEREST,
                        Constants.INTERESTS.NO_INTEREST,
                        false,
                        "0.0",
                        "0.0",
                        false,
                        "",
                        "");*/

        return userPreference;
    }

}
