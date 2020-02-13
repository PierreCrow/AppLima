package com.avances.applima.presentation.utils;

public class Constants {


    public class URLS {
        public static final String URL_BASE = "http://200.37.138.11:8087/ServicioAppLimaRest/api/";
        public static final String TOTAL_SYNCHRONIZATION = "sincronizacion/ObtenerSincronizacionTotal";
        public static final String GENERATE_TOKEN = "Autenticacion/GenerarToken";
        public static final String REGISTER_TEMPORAL_USER = "seguridad/RegistrarUsuarioTemporal";
        public static final String REGISTER_USER = "seguridad/RegistrarUsuario";
        public static final String RESEND_CODE = "seguridad/ReenviarCodigo";
        public static final String LOGIN = "seguridad/IniciarSesion";
        public static final String GET_USER = "seguridad/ObtenerUsuario";
        public static final String FORGOT_PASSWORD = "seguridad/OlvideContrasena";
        public static final String VALIDATE_CODE = "seguridad/ValidarCodigo";
        public static final String LOGIN_SOCIAL_MEDIA = "seguridad/LoginRedSocial";
        public static final String ROUTES_BY_INTEREST = "seguridad/ListarRutasPorInteres";
        public static final String FAVORITES_BY_USER = "seguridad/ListaFavoritoPorUsuario";
        public static final String UPDATE_USER = "seguridad/ActualizarUsuario";
    }


    public static class STORE {
        public static final int CLOUD = 0;
        public static final int DB = 1;
    }

    public static class REQUEST_CODES {
        public static final int REQUEST_CODE_LOCATION = 123;
        public static final int REQUEST_CODE_STORAGE = 456;
        public static final int REQUEST_CODE_CALENDAR = 789;
        public static final int REQUEST_CODE_CAMERA = 966;
    }

    public class PREFERENCES {
        public static final String PREFERENCE_CURRENT_USER = "PREFERENCE_CURRENT_USER";
    }

    public class PREFERENCES_KEYS {
        public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
        public static final String CURRENT_USER_NAME = "CURRENT_USER_NAME";
        public static final String CURRENT_USER_LAST_NAME = "CURRENT_USER_LAST_NAME";
        public static final String CURRENT_USER_EMAIL = "CURRENT_USER_EMAIL";
        public static final String CURRENT_USER_PASSWORD = "CURRENT_USER_PASSWORD";
        public static final String CURRENT_USER_PHONE = "CURRENT_USER_PHONE";
        public static final String CURRENT_USER_COUNTRY = "CURRENT_USER_COUNTRY";
        public static final String CURRENT_USER_IMAGE = "CURRENT_USER_IMAGE";
        public static final String CURRENT_USER_LOGIN_TYPE = "CURRENT_USER_LOGIN_TYPE";
        public static final String CURRENT_USER_SECONDS_VIEWED = "CURRENT_USER_SECONDS_VIEWED";
        public static final String CURRENT_USER_LOGGED = "CURRENT_USER_LOGGED";
        public static final String CURRENT_USER_HAS_INTERESTS = "CURRENT_USER_HAS_INTERESTS";
        public static final String CURRENT_USER_INTEREST_1 = "CURRENT_USER_INTEREST_1";
        public static final String CURRENT_USER_INTEREST_2 = "CURRENT_USER_INTEREST_2";
        public static final String CURRENT_USER_INTEREST_3 = "CURRENT_USER_INTEREST_3";
        public static final String CURRENT_USER_INTEREST_4 = "CURRENT_USER_INTEREST_4";
        public static final String CURRENT_USER_INTEREST_5 = "CURRENT_USER_INTEREST_5";
        public static final String CURRENT_USER_FIRST_SYNC_SUCCESS = "CURRENT_USER_FIRST_SYNC_SUCCESS";
        public static final String CURRENT_USER_LAT = "CURRENT_USER_LAT";
        public static final String CURRENT_USER_LNG = "CURRENT_USER_LNG";
        public static final String CURRENT_USER_HAS_LOCATION = "CURRENT_USER_HAS_LOCATION";
        public static final String CURRENT_USER_TOKEN_FCM = "CURRENT_USER_TOKEN_FCM";
        public static final String CURRENT_USER_ID_TEMPORAL = "CURRENT_USER_ID_TEMPORAL";

        public static final String CURRENT_USER_ADDRESS = "CURRENT_USER_ADDRESS";
        public static final String CURRENT_USER_GENDER = "CURRENT_USER_GENDER";
        public static final String CURRENT_USER_BIRTH_DATE = "CURRENT_USER_BIRTH_DATE";
        public static final String CURRENT_USER_TOKEN = "CURRENT_USER_TOKEN";

    }


    public class INTERESTS {
        public static final int HISTORIA = 0;
        public static final int CULTURA = 1;
        public static final int GASTRONOMIA = 2;
        public static final int ARTE = 3;
        public static final int AVENTURA = 4;
        public static final int NO_INTEREST = 5;
    }

    public class LOGIN_TYPES {
        public static final int GOOGLE = 0;
        public static final int FACEBOOK = 1;
        public static final int EMAIL = 2;
        public static final int NOT_LOGIN = 3;
    }

    public class FRAGMENTS_TABS {
        public static final int HOME = 0;
        public static final int FAVORITOS = 1;
        public static final int EVENTOS = 2;
        public static final int CUENTA = 3;
    }


    public class REGISTER_TYPES {
        public static final String EMAIL = "TIRE0003";
        public static final String FACEBOOK = "TIRE0002";
        public static final String GOOGLE = "TIRE0001";
        public static final String NOT_LOGIN = "TIRE00000005";
    }

    public class REGISTER_STATES {
        public static final String EMAIL = "TIRE0003";
        public static final String FACEBOOK = "TIRE0002";
    }

    public class SYSTEM {
        public static final String APP = "TISI0001";
    }

    public class RESPONSE_CODES {
        public static final String SUCCESS = "01";
    }

    public class RESPONSE_MESSAGES {
        public static final String ERROR = "Error al conectarse al servidor";
    }



    // PARAMETER VALUE SECTION




}


