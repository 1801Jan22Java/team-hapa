export interface reservation {
    id : number,
    endUser : {
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
    flight: {
        id: number,
        gate: number,
        time: Date,
        cost: number,
        duration: number,
        type: {
            id: number,
            refKey: string,
            refValue: string
        },
        city: {
            id: string,
            name: string,
            latitude: string,
            population: string,
            longitude: string,
            state: string,
            country: {
                id: number,
                name: string,
                reservations: any[]
            }
        },
        typeId: {
            id: number,
            refKey: string,
            refValue: string
        },
        userId: {
            id: number,
            name: string,
            latitude: number,
            population: number,
            longitude: number,
            state: string,
            country: {
                id: number,
                name: string
            }
        }
    },
    creationDate : Date,
    status : {
        id: number,
        refKey: string,
        refValue: string
    },
    type : {
        id: number,
        refKey: string,
        refValue: string
    }
}