package com.avances.lima.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.avances.lima.R;
import com.avances.lima.domain.model.UserPreference;
import com.avances.lima.domain.model.Usuario;
import com.avances.lima.presentation.presenter.UsuarioPresenter;
import com.avances.lima.presentation.ui.dialogfragment.TermsConditionsDialog;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.utils.TransparentProgressDialog;
import com.avances.lima.presentation.view.UsuarioView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
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

import java.util.List;

import butterknife.BindView;

public class LoginActivity extends BaseActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, UsuarioView {

    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.tvIniciarSesionLoginActivity)
    TextView tvIniciarSesionLoginActivity;
    @BindView(R.id.tvRegistroLoginActivity)
    TextView tvRegistroLoginActivity;
    @BindView(R.id.rlGoogle)
    RelativeLayout rlGoogle;
    @BindView(R.id.rlFacebooko)
    RelativeLayout rlFacebook;
    @BindView(R.id.cbPoliticas)
    CheckBox cbPoliticas;
    @BindView(R.id.cbTerminos)
    CheckBox cbTerminos;

    private FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;
    final static int RC_SIGN_IN = 9001;
    LoginButton btnLoginFb;
    CallbackManager callbackManager;
    String id, nameUser, lastNameUser, emailUser, pictureUser;
    UsuarioPresenter usuarioPresenter;
    TransparentProgressDialog loading;
    SingleClick singleClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
        setContentView(R.layout.login_activity);

        injectView();
        loadPresenter();
        initUI();
        facebookIntegration();

    }



    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.ivClose:

                        if(Helper.getUserAppPreference(getApplicationContext()).isLogged())
                        {

                        }

                        finish();
                        break;
                    case R.id.tvIniciarSesionLoginActivity:
                        next(LoginEmailActivity.class, null);
                        break;
                    case R.id.tvRegistroLoginActivity:
                        next(RegisterUserActivity.class, null);
                        break;
                    case R.id.rlGoogle:
                        if (cbPoliticas.isChecked() && cbTerminos.isChecked()) {
                            signInwithGoogle();
                        } else {
                            Toast.makeText(getApplicationContext(), "Acepte Terminos y Condiciones", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.rlFacebooko:
                        if (cbPoliticas.isChecked() && cbTerminos.isChecked()) {
                            btnLoginFb.performClick();
                        } else {
                            Toast.makeText(getApplicationContext(), "Acepte Terminos y Condiciones", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.cbPoliticas:
                        if (cbPoliticas.isChecked()) {
                            TermsConditionsDialog df = new TermsConditionsDialog();
                            // df.setArguments(args);
                            df.show(getSupportFragmentManager(), "TermsConditionsDialog");
                        }
                        break;
                    case R.id.cbTerminos:
                        if (cbTerminos.isChecked()) {
                            TermsConditionsDialog df = new TermsConditionsDialog();
                            // df.setArguments(args);
                            df.show(getSupportFragmentManager(), "TermsConditionsDialog");
                        }
                        break;
                }
            }
        };
    }


    void loadPresenter() {
        usuarioPresenter = new UsuarioPresenter();
        usuarioPresenter.addView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    void initUI() {

        mAuth = FirebaseAuth.getInstance();
        buildGoogleApiClient();

        onClickListener();
        ivClose.setOnClickListener(singleClick);
        tvIniciarSesionLoginActivity.setOnClickListener(singleClick);
        tvRegistroLoginActivity.setOnClickListener(singleClick);
        rlGoogle.setOnClickListener(singleClick);
        rlFacebook.setOnClickListener(singleClick);
        cbPoliticas.setOnClickListener(singleClick);
        cbTerminos.setOnClickListener(singleClick);

        loading = new TransparentProgressDialog(getContext());

       // cbPoliticas.setChecked(true);
       // cbTerminos.setChecked(true);

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
                .requestIdToken(getString(com.avances.lima.R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

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

            id = object.getString("id");
            nameUser = object.getString("first_name");
            lastNameUser = object.getString("last_name");
            nameUser = nameUser + " " + lastNameUser;
            emailUser = object.getString("email");
            pictureUser = "https://graph.facebook.com/" + id + "/picture?width=200&height=150";

            userPreference.setName(nameUser);
            userPreference.setLastName(lastNameUser);
            userPreference.setEmail(emailUser);
            userPreference.setImage(pictureUser);

            return userPreference;
        } catch (JSONException e) {
            String eee = e.getMessage();
        }
        return null;
    }

    private UserPreference getGoogleData(GoogleSignInAccount acct) {
        UserPreference userPreference = Helper.getUserAppPreference(getApplicationContext());

        nameUser = acct.getGivenName();
        lastNameUser = acct.getFamilyName();
        emailUser = acct.getEmail();
        String personId = acct.getId();
        pictureUser = acct.getPhotoUrl().toString();

        userPreference.setName(nameUser);
        userPreference.setLastName(lastNameUser);
        userPreference.setEmail(emailUser);
        userPreference.setImage(pictureUser);
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

                        usuarioPresenter.loginSocialMedia(Helper.getUserAppPreference(getContext()).getToken(), userPreference.getEmail(), userPreference.getName(), Constants.SYSTEM.APP, Constants.REGISTER_TYPES.FACEBOOK, userPreference.getIdTemporal());
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

                usuarioPresenter.loginSocialMedia(Helper.getUserAppPreference(getContext()).getToken(), userPreference.getEmail(), userPreference.getName(), Constants.SYSTEM.APP, Constants.REGISTER_TYPES.GOOGLE, userPreference.getIdTemporal());

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
        if (usuario.getRegisterState().equals("ESRE0001")) {
            next(CompleteInfoActivity.class, null);
        } else {
            next(MainActivity.class, null);
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
    public void versionApp(String version) {

    }

    @Override
    public void imageUploaded(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {

        if (loading.isShowing()) {
            loading.dismiss();
        }

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public Context getContext() {
        return this;
    }


}