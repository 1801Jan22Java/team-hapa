export interface RegistrationInfo {
    firstname: string,
    lastname: string,
    email: string, //- check for unique, if already exists, give an error. 
    password: string,//- whatever they send in the first password field, that’s what we save
    type: string,  //(just send as a string; not part of form)
    answer1: string,
    answer2: string,
    answer3: string,
}