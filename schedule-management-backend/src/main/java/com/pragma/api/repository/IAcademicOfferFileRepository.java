package com.pragma.api.repository;

import com.pragma.api.model.AcademicOfferFile;
import com.pragma.api.model.enums.StateAcOfferFileEnumeration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAcademicOfferFileRepository extends JpaRepository<AcademicOfferFile, Integer>{

    /**
     * Encuentra un archivo de oferta academica por el id del programa, el id del periodo y el estado
     * del archivo que deber ser diferente de FINALIZADO
     * @param programId
     * @param periodId
     * @param stateFile
     * @return
     */
    public AcademicOfferFile findByProgram_ProgramIdAndPeriod_PeriodIdAndStateFileNot(
            String programId, String periodId, StateAcOfferFileEnumeration stateFile);

    public AcademicOfferFile findByProgram_ProgramIdAndPeriod_PeriodIdAndStateFile(
            String programId, String periodId, StateAcOfferFileEnumeration stateFile);

}
