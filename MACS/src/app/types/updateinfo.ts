export interface updateinfo {
    firstname: string,
    lastname: string,
    email: string, //- check for unique, if already exists, give an error. 
    newpassword: string,//- whatever they send in the first password field, that’s what we save
    answer1: string,
    answer2: string,
    answer3: string
}