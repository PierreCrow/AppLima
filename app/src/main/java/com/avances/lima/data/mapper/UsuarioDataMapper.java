package com.avances.lima.data.mapper;

import android.content.Context;

import com.avances.lima.data.datasource.cloud.model.security.response.WsDataUser;
import com.avances.lima.data.datasource.cloud.model.security.response.WsDataUserLoginSocialMedia;
import com.avances.lima.domain.model.UserPreference;
import com.avances.lima.domain.model.Usuario;
import com.avances.lima.presentation.utils.Helper;


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
        userPreference.setBirthDate(usuario.getBirthDate());
        userPreference.setName(usuario.getName());
        userPreference.setRegisterLoginType(usuario.getRegisterType());
        userPreference.setGender(usuario.getSex());

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
