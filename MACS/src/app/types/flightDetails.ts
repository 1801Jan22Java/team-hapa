import { flight } from "./flight";

export interface flightDetails {
    flight: flight,
    status: {
        id: number,
        refKey: string,
        refValue: string
    }
}