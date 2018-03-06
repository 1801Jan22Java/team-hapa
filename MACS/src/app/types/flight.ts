export interface flight {
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
}