package BloodManagement.ServerSide.Service;

import BloodManagement.ServerSide.CoreExceptions.InvalidIDException;
import BloodManagement.ServerSide.CoreExceptions.NullArgumentException;
import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.Drug;
import BloodManagement.ServerSide.Repository.drug.DrugRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DrugService {
    public static final Logger log = LoggerFactory.getLogger(DrugService.class);

    @Autowired
    private DrugRepository drugRepository;


    /**
     * @param drugId : An id to map the Drug to be created. It can't be null.
     * @param drugName : An string representing the drug name. It can't be null.
     * @param recommendedAmountPerDay : An int representing the recommendedAmountPerDay for the drug. It can't be null.
     * @throws ValidationException : throws this exception if the given params can't get past the validation from the repo.
     * @throws NullArgumentException : throws this exception if any of the params are null.
     * @return a Drug instance with the given params.
     */

    public Drug addDrug(Integer drugId, String drugName, Integer recommendedAmountPerDay) throws ValidationException {

        // foreach drugId in drugsIds get each drug and form an arraylist of drugs
        try{
            log.trace("addDrug --- method entered");

            Drug drug = new Drug();
            drug.setId(drugId);
            drug.setName(drugName);
            drug.setRecommendedAmount(recommendedAmountPerDay);
            return drugRepository.existsById(drugId)?null:drugRepository.save(drug);
        }catch (NullPointerException exception) {
            throw new NullArgumentException("Argument of addDrug was null");
        }finally{
            log.trace("addDrug --- method exited");
        }
    }

    /**
     * @param drugId : An id to get the old Drug and update its values. It can't be null.
     * @param drugName : An string representing the drug name. It can't be null.
     * @param recommendedAmountPerDay : An int representing the recommendedAmountPerDay for the drug. It can't be null.
     * @throws ValidationException: throws this exception if the given params can't get past the validation from the repo.
     * @throws NullArgumentException: throws this exception if any of the params are null.
     * @return a Drug instance with the updated values which were saved in the repository.
     */
    public Drug updateDrug(Integer drugId,String drugName, Integer recommendedAmountPerDay) throws ValidationException{
        try{
            log.trace("updateDrug --- method entered");

            Objects.requireNonNull(drugName);
            Objects.requireNonNull(recommendedAmountPerDay);
            Drug dbDrug = drugRepository.findById(Objects.requireNonNull(drugId)).orElseThrow(()->{throw new InvalidIDException("The given drug doesn't exist in the repo");});
            Drug drug = new Drug();
            drug.setId(drugId);
            drug.setName(drugName);
            drug.setRecommendedAmount(recommendedAmountPerDay);
            return drugRepository.save(drug);
        }catch (NullPointerException exception){
            throw new NullArgumentException("Argument of updatePrescription was null");
        }finally {
            log.trace("updateDrug --- method entered");
        }
    }

    /**
     * @param drugId : An id which maps a certain Drug which is to be deleted. It can't be null.
     * @throws NullArgumentException: throws this exception if any of the params are null.
     * @return a Drug instance which was deleted from the repo.
     */

    public Drug deleteDrug(Integer drugId){
        try{
            log.trace("deleteDrug --- method entered");

            Drug drugToBeDeleted = drugRepository.findById(drugId).orElse(null);
            if(drugToBeDeleted == null)
                return null;
            drugRepository.deleteById(Objects.requireNonNull(drugId));
            return drugToBeDeleted;
        }catch (NullPointerException exception){
            throw new NullArgumentException("Argument of deleteDrug was null");
        }finally{
            log.trace("deleteDrug --- method exited");
        }
    }

    /**
     * @param drugId : An id which maps a certain Drug which is to be read. It can't be null.
     * @throws NullArgumentException: throws this exception if any of the params are null.
     * @return a Drug instance which was found in the repo.
     */
    public Drug getDrug(Integer drugId){
        try {
            log.trace("getDrug --- method entered");

            var drug =  drugRepository.findById(Objects.requireNonNull(drugId)).orElse(null);
            log.trace("getDrug --- method exited");

            return drug;


        }catch (NullPointerException exception){
            throw new NullArgumentException("The argument of getDrug was null");
        }
    }

    public Collection<Drug> getAllDrugs(){
        log.trace("getAllDrugs --- method entered");

        var drugs = (Collection<Drug>) drugRepository.findAll();

        log.trace("getAllDrugs --- method exited");
        return drugs;

    }


    public Collection<Drug> getDrugsWithDoseBiggerThan(Integer recommendedAmount){
        try{
            log.trace("getDrugsWithDoseBiggerThen --- method entered");

            Collection<Drug> drugs = (Collection<Drug>)drugRepository.findAllByRecommendedAmountGreaterThanEqual(recommendedAmount);
            log.trace("getDrugsWithDoseBiggerThen --- method exited");
            return drugs;
        }catch (NullPointerException exception){
            throw new NullArgumentException("The argument recommendedAmount was null!");
        }
    }

    public Collection<Drug> getDrugsWithDosesOf(){
        log.trace("getDrugsWithDoseOf--- method entered");

        Collection<Drug> drugs = (Collection<Drug>)drugRepository.findAll();
        var filteredDrugs =  drugs.stream().filter(drug -> drug.getRecommendedAmount()==3).collect(Collectors.toList());
        log.trace("getDrugsWithDoseOf--- method entered");
        return filteredDrugs;

    }
}
