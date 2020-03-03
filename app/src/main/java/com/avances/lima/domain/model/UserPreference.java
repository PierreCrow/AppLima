package com.avances.lima.domain.model;

public class UserPreference {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private String pass;
    private String phone;
    private String country;
    private String image;
    private String registerLoginType;
    private boolean logged;
    private boolean secondsToOfferViewed;
    private boolean hasInterests;
    private String interest_1;
    private String interest_2;
    private String interest_3;
    private String interest_4;
    private String interest_5;
    private boolean firstSyncSuccess;
    private String lat;
    private String lng;
    boolean hasLocation;
    private String tokenFCM;
    private String idTemporal;
    private String address;
    private String gender;
    private String birthDate;
    private String token;
    private String permanencyDays;
    private boolean lastVersion;


    public UserPreference(String id,String name, String lastName, String email, String pass,
                          String phone, String country, String image, String registerLoginType,
                          boolean logged, boolean secondsToOfferViewed, boolean hasInterests,
                          String interest_1, String interest_2, String interest_3, String interest_4, String interest_5,
                          boolean firstSyncSuccess, String lat, String lng, boolean hasLocation,
                          String tokenFCM,String idTemporal,String address,String gender,String birthDate,String token,
                          String permanencyDays,boolean lastVersion) {

        this.id=id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.country = country;
        this.image = image;
        this.registerLoginType = registerLoginType;
        this.logged = logged;
        this.secondsToOfferViewed = secondsToOfferViewed;

        this.hasInterests = hasInterests;
        this.interest_1 = interest_1;
        this.interest_2 = interest_2;
        this.interest_3 = interest_3;
        this.interest_4 = interest_4;
        this.interest_5 = interest_5;
        this.firstSyncSuccess = firstSyncSuccess;
        this.lat = lat;
        this.lng = lng;
        this.hasLocation = hasLocation;
        this.tokenFCM = tokenFCM;
        this.idTemporal=idTemporal;

        this.address=address;
        this.gender=gender;
        this.birthDate=birthDate;
        this.token=token;

        this.permanencyDays=permanencyDays;

        this.lastVersion=lastVersion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRegisterLoginType() {
        return registerLoginType;
    }

    public void setRegisterLoginType(String registerLoginType) {
        this.registerLoginType = registerLoginType;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isSecondsToOfferViewed() {
        return secondsToOfferViewed;
    }

    public void setSecondsToOfferViewed(boolean secondsToOfferViewed) {
        this.secondsToOfferViewed = secondsToOfferViewed;
    }

    public boolean isHasInterests() {
        return hasInterests;
    }

    public void setHasInterests(boolean hasInterests) {
        this.hasInterests = hasInterests;
    }


    public String getInterest_1() {
        return interest_1;
    }

    public void setInterest_1(String interest_1) {
        this.interest_1 = interest_1;
    }

    public String getInterest_2() {
        return interest_2;
    }

    public void setInterest_2(String interest_2) {
        this.interest_2 = interest_2;
    }

    public String getInterest_3() {
        return interest_3;
    }

    public void setInterest_3(String interest_3) {
        this.interest_3 = interest_3;
    }

    public String getInterest_4() {
        return interest_4;
    }

    public void setInterest_4(String interest_4) {
        this.interest_4 = interest_4;
    }

    public String getInterest_5() {
        return interest_5;
    }

    public void setInterest_5(String interest_5) {
        this.interest_5 = interest_5;
    }

    public boolean isFirstSyncSuccess() {
        return firstSyncSuccess;
    }

    public void setFirstSyncSuccess(boolean firstSyncSuccess) {
        this.firstSyncSuccess = firstSyncSuccess;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public boolean isHasLocation() {
        return hasLocation;
    }

    public void setHasLocation(boolean hasLocation) {
        this.hasLocation = hasLocation;
    }

    public String getTokenFCM() {
        return tokenFCM;
    }

    public void setTokenFCM(String tokenFCM) {
        this.tokenFCM = tokenFCM;
    }

    public String getIdTemporal() {
        return idTemporal;
    }

    public void setIdTemporal(String idTemporal) {
        this.idTemporal = idTemporal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPermanencyDays() {
        return permanencyDays;
    }

    public void setPermanencyDays(String permanencyDays) {
        this.permanencyDays = permanencyDays;
    }

    public boolean isLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(boolean lastVersion) {
        this.lastVersion = lastVersion;
    }
}
