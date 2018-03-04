export interface auth {
    userID: number,
    firstName: string,
    type: {
        id: number,
        refKey: string,
        refValue: string
    },
}