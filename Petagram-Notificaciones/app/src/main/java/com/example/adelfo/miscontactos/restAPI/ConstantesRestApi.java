package com.example.adelfo.miscontactos.restAPI;

/**
 * Created by Adelfo on 22/11/2016.
 */

public final class ConstantesRestApi {
    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;

    public static final String KEY_GET_RECENT_MEDIA_X_USER = "users/{user_id}/media/recent";
    public static final String KEY_SEARCH = "users/search";

    public static final String TOKEN_APPLICATION = "4179481767.6a67fa6.ca3f237670924e89b088d8a088a08216";

    //Busqueda de un usuario mediante su username y obtencion de su identificador para ponerlo en el tab de contacto principal
    //https://api.instagram.com/v1/users/search
    public static final String URL_SEARCH_BY_USERNAME = ROOT_URL + KEY_SEARCH;

    //Obtencion de los medios recientes del usuario asignado en el tab de contacto principal y en el timeline
    //https://api.instagram.com/v1/users/{user_id}/media/recent
    public static final String URL_GET_RECENT_MEDIA_X_USER = ROOT_URL + KEY_GET_RECENT_MEDIA_X_USER;

    //Identificador de los usuarios de quienes se obtendran los medios recientes para mostrar solo en el tab de timeline
    //Usuario 1
    public static final String id_foto_example ="1364177164312137905_3491459620";

    public static final String default_user_id = "4179481767";
    public static final String default_user_name = "perritocheno";

    public static final String user0 = "4179481767";    //perritocheno
    public static final String user1 = "3491459620";    //perritotoby
    public static final String user2 = "4183797308";    //devnaila87
    public static final String user3 = "4178937654";    //asthmaticsquid

    public static final String ROOT_URL_FIREBASE = "https://warm-reaches-74617.herokuapp.com/";
    public static final String KEY_POST_REGISTRAR_USUARIO = "registrar-usuario/";

    public static final String KEY_TOQUE_ANIMAL = "toque-animal/{id_dispositivo}/{id_usuario_instagram}/{nombre_usuario_instagram}/{id_foto_instagram}";

    public static final String KEY_FOLLOW_UNFOLLOW = "follow-unfollow/";
}

