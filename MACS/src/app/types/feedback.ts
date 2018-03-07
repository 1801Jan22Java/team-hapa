export interface feedback {
    id: number,
    endUser: {
        id: number,
        firstname: string,
        lastname: string,
        email: string,
        password: string,
        type: {
            id: number,
            refKey: string,
            refValue: string
        },
        creationDate: Date,
        secretAnswer1: string,
        secretAnswer2: string,
        secretAnswer3: string
    },
    message: string,
    creationDate: Date
}