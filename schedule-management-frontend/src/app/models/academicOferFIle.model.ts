import { Period } from './period.model';
import { Program } from './program.model';
export interface academicOferFile {
    id: number;
    nameFile: string;
    dateRegisterFile: Date;
    stateFile: string;
    period: Period;
    program: Program;
}