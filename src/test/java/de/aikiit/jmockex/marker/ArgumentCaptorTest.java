package de.aikiit.jmockex.marker;

import de.aikiit.jmockex.ASimpleParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.nio.file.Path;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentCaptorTest {

    @Mock
    private Path path;

    @InjectMocks
    private ASimpleParser parser;

    @Test
    public void exampleWithInjectableMocks() {

        /*
        public FieldValidation add(Field newField, UUID companyId, UUID farmId) {
            FieldValidation creationResult = new FieldValidation(FieldImportStateEnum.FAILED, newField);

            if (newField != null) {
                FieldDTO newFieldDTO = FieldFieldDTOConverter.convertToFieldDTO(newField);
                newFieldDTO.setCompanyId(companyId);
                newFieldDTO.setFarm(farmService.getFarm(farmId));
                FieldDTO fieldRes = fieldService.createField(newFieldDTO);
                FieldFieldDTOConverter.copyValues(fieldRes, creationResult.getField());
                updateResultStatus(fieldRes, creationResult);
            }
            return creationResult;
            */

        // wenn fieldValidation nicht getFIeldDTO hat, könnte man mit ArgCaptor abgreifen,
        // dass zB bei Umwandlung des FieldDTO ein getName() aufgerufen wird und dann auch wirklich der Name drin ist, der übergeben wurde

    }


}
