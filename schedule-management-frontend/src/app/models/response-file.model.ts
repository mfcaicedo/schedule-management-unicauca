export interface ResponseFile {
    statusFile: string;
    contRows: number;
    contErrorRows: number;
    contSuccessRows: number;
    contSaveRows: number;
    logsType: Array<string>;
    logsEmptyFields: Array<string>;
    logsGeneric: Array<string>;
    logsSuccess: Array<string>;
}