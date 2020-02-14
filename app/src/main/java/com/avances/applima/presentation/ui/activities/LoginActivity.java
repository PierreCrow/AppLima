package com.avances.applima.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.avances.applima.R;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.domain.model.Usuario;
import com.avances.applima.presentation.presenter.UsuarioPresenter;
import com.avances.applima.presentation.ui.dialogfragment.TermsAndCondition;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.utils.TransparentProgressDialog;
import com.avances.applima.presentation.view.UsuarioView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends BaseActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, UsuarioView {

    ImageView ivClose;
    TextView tvRegistroLoginActivity, tvIniciarSesionLoginActivity;
    RelativeLayout rlGoogle, rlFacebook;
    private FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;
    final static int RC_SIGN_IN = 9001;
    LoginButton btnLoginFb;
    CallbackManager callbackManager;
    String ID, NOMBRE, APELLIDO, EMAIL, URLFOTO;
    String KEYHASH;
    UsuarioPresenter usuarioPresenter;

    TransparentProgressDialog loading;

    CheckBox   cbPoliticas,cbTerminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
        setContentView(R.layout.login_activity);

        loadPresenter();

        initUI();

        facebookIntegration();

        clickEvents();
    }


    void loadPresenter()
    {
        usuarioPresenter= new UsuarioPresenter();
        usuarioPresenter.addView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    void initUI() {

       // FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        buildGoogleApiClient();

        loading = new TransparentProgressDialog(getContext());

        ivClose = (ImageView) findViewById(R.id.ivClose);
        tvIniciarSesionLoginActivity = (TextView) findViewById(R.id.tvIniciarSesionLoginActivity);
        tvRegistroLoginActivity = (TextView) findViewById(R.id.tvRegistroLoginActivity);

        rlGoogle = (RelativeLayout) findViewById(R.id.rlGoogle);
        rlFacebook = (RelativeLayout) findViewById(R.id.rlFacebooko);

        cbPoliticas=(CheckBox)findViewById(R.id.cbPoliticas);
        cbTerminos=(CheckBox)findViewById(R.id.cbTerminos);


        cbPoliticas.setChecked(true);
        cbTerminos.setChecked(true);

        // getKeyHasgh();
    }


    protected synchronized void buildGoogleApiClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();

    }

    void facebookIntegration() {
        btnLoginFb = (LoginButton) findViewById(R.id.connectWithFbButton);
        callbackManager = CallbackManager.Factory.create();

        btnLoginFb.setReadPermissions("email");
        FacebookSdk.sdkInitialize(getApplicationContext());

        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);

        callbackFacebook();
    }

    protected void signInwithGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(com.avances.applima.R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //manda a la activity con resultado
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void authFirebaseGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                        } else {
                            Toast.makeText(getApplicationContext(), "Connected To Firebase Gooogle Failure.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void authFirebaseFacebook(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                        } else {
                            Toast.makeText(LoginActivity.this, "Facebook not connected to Firebase",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private UserPreference getFacebookData(JSONObject object) {

        try {

            UserPreference userPreference = Helper.getUserAppPreference(getApplicationContext());

            ID = object.getString("id");
            NOMBRE = object.getString("first_name");
            APELLIDO = object.getString("last_name");
            EMAIL = object.getString("email");
            URLFOTO = "https://graph.facebook.com/" + ID + "/picture?width=200&height=150";

            userPreference.setName(NOMBRE);
            userPreference.setLastName(APELLIDO);
            userPreference.setEmail(EMAIL);
            userPreference.setImage(URLFOTO);

            return userPreference;
        } catch (JSONException e) {
            String eee = e.getMessage();
        }
        return null;
    }

    private UserPreference getGoogleData(GoogleSignInAccount acct) {
        UserPreference userPreference = Helper.getUserAppPreference(getApplicationContext());

        NOMBRE = acct.getGivenName();
        APELLIDO = acct.getFamilyName();
        EMAIL = acct.getEmail();
        String personId = acct.getId();
        URLFOTO = acct.getPhotoUrl().toString();

        userPreference.setName(NOMBRE);
        userPreference.setLastName(APELLIDO);
        userPreference.setEmail(EMAIL);
        userPreference.setImage(URLFOTO);
        Helper.saveUserAppPreference(getApplicationContext(), userPreference);

        return userPreference;
    }

    void callbackFacebook() {
        btnLoginFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {


            @Override
            public void onSuccess(LoginResult loginResult) {
                String accessToken = loginResult.getAccessToken().getToken();
                authFirebaseFacebook(loginResult.getAccessToken());

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Get facebook data from login
                        UserPreference userPreference = getFacebookData(object);
                        userPreference.setRegisterLoginType(Constants.REGISTER_TYPES.FACEBOOK);
                        userPreference.setLogged(true);
                        Helper.saveUserAppPreference(getApplicationContext(), userPreference);

                        if (!loading.isShowing()) {
                            loading.show();
                        }

                        usuarioPresenter.loginSocialMedia(Helper.getUserAppPreference(getContext()).getToken(),userPreference.getEmail(),userPreference.getName(),Constants.SYSTEM.APP,Constants.REGISTER_TYPES.FACEBOOK,userPreference.getIdTemporal());
                       // usuarioPresenter.registerUser(userPreference.getName(),"","","","",userPreference.getEmail(),userPreference.getPass(),"","","");
                       // next(CompleteInfoActivity.class,null);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();
            }


            @Override
            public void onCancel() {
                System.out.println("onCancel");

             /*   if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                loginGoogle.setEnabled(true);
                loginn.setEnabled(true);*/
            }

            @Override
            public void onError(FacebookException exception) {
                String exce = exception.toString();
/*
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                loginGoogle.setEnabled(true);
                loginn.setEnabled(true);*/

                Toast toast = Toast.makeText(getApplicationContext(), "Error al conectarse a Facebook", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }

    void clickEvents() {
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        tvIniciarSesionLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                next(LoginEmailActivity.class,null);

            }
        });

        tvRegistroLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                next(RegistroActivity.class,null);

            }
        });


        rlGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbPoliticas.isChecked() && cbTerminos.isChecked())
                {
                    signInwithGoogle();
                }
                else
                {
                    Toast toast=Toast. makeText(getApplicationContext(),"Acepte Terminos y Condiciones",Toast. LENGTH_SHORT);
                    toast. setMargin(50,50);
                    toast. show();
                }


            }
        });

        rlFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbPoliticas.isChecked() && cbTerminos.isChecked())
                {
                    btnLoginFb.performClick();
                }
                else
                {
                    Toast toast=Toast. makeText(getApplicationContext(),"Acepte Terminos y Condiciones",Toast. LENGTH_SHORT);
                    toast. setMargin(50,50);
                    toast. show();
                }


            }
        });


        cbPoliticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbPoliticas.isChecked())
                {
                TermsAndCondition df = new TermsAndCondition();
                // df.setArguments(args);
                df.show(getSupportFragmentManager(), "TermsAndCondition");}

            }
        });

        cbTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbTerminos.isChecked())
                {
                    TermsAndCondition df = new TermsAndCondition();
                    // df.setArguments(args);
                    df.show(getSupportFragmentManager(), "TermsAndCondition");
                }


            }
        });




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();

                UserPreference userPreference = getGoogleData(acct);
                userPreference.setRegisterLoginType(Constants.REGISTER_TYPES.GOOGLE);
                userPreference.setLogged(true);
                Helper.saveUserAppPreference(getApplicationContext(), userPreference);

                if (!loading.isShowing()) {
                    loading.show();
                }

                usuarioPresenter.loginSocialMedia(Helper.getUserAppPreference(getContext()).getToken(),userPreference.getEmail(),userPreference.getName(),Constants.SYSTEM.APP,Constants.REGISTER_TYPES.GOOGLE,userPreference.getIdTemporal());

                authFirebaseGoogle(acct);
               // next(CompleteInfoActivity.class,null);

            } else {
                //   loginGoogle.setEnabled(true);
                //  loginn.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Error to connect Google", Toast.LENGTH_SHORT).show();
            }
        }

        //facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);//para facebook

    }


    void comprobarEstadoInicioSesionfacebook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    void getKeyHasgh() {
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.avances.applima", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                KEYHASH = something;
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }


        KEYHASH = KEYHASH + "   " + KEYHASH;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void temporalUserRegistered(String idTempUser) {

    }

    @Override
    public void tokenGenerated(String token) {

    }

    @Override
    public void userRegistered(Usuario usuario) {

    }

    @Override
    public void loginSuccess(Usuario usuario) {

    }

    @Override
    public void loginSocialMediaSuccess(Usuario usuario) {

        if (loading.isShowing()) {
            loading.dismiss();
        }

        //validar si mandamos a completar datops o al home
        if(usuario.getRegisterState().equals("ESRE0001"))
        {
            next(CompleteInfoActivity.class,null);
        }
        else
        {
            next(MainActivity.class,null);
        }
    }

    @Override
    public void forgotPasswordSuccess(String message) {

    }

    @Override
    public void reSendCodeSuccess(String message) {

    }

    @Override
    public void userGot(Usuario usuario) {

    }

    @Override
    public void validateCodeSuccess(Usuario message) {

    }

    @Override
    public void routesByInterestSuccess(List<String> idRoutes) {

    }

    @Override
    public void userUpdated(Usuario usuario) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}