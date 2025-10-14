package business;

import entities.Module;
import entities.UniteEnseignement;
import business.UniteEnseignementBusiness;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModuleBusiness {
    private static List<Module> modules;
private UniteEnseignementBusiness uniteEnseignementBusiness=new UniteEnseignementBusiness();
    public ModuleBusiness() {
        modules = new ArrayList<Module>();
        // Initialisation avec quelques données de test
        modules.add(new Module("M101", "Algorithmique", 3, 30, Module.TypeModule.PROFESSIONNEL,uniteEnseignementBusiness.getUEByCode(1)));
        modules.add(new Module("M102", "Base de données", 2, 20, Module.TypeModule.PROFESSIONNEL,uniteEnseignementBusiness.getUEByCode(1)));
        modules.add(new Module("M201", "Communication", 1, 15, Module.TypeModule.TRANSVERSAL,uniteEnseignementBusiness.getUEByCode(2)));
    }

   
    public boolean addModule(Module module) {

        int code=module.getUniteEnseignement().getCode();
       UniteEnseignement ue=uniteEnseignementBusiness.getUEByCode(code);
       if(ue!=null){
           module.setUniteEnseignement(ue);
           return modules.add(module);
    }
        return false;
}


    public Module getModuleByMatricule(String matricule) {
        for (Module m : modules) {
            if (m.getMatricule().equals(matricule)) {
                return m;
            }
        }
        return null;
    }

    public List<Module> getModulesByType(Module.TypeModule type) {
        List<Module> result = new ArrayList<>();
        for (Module m : modules) {
            if (m.getType() == type) {
                result.add(m);
            }
        }
        return result;
    }

    // Mettre à jour un module
    public boolean updateModule(String matricule, Module updatedModule) {
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getMatricule().equals(matricule)) {
                modules.set(i, updatedModule);
                return true;
            }
        }
        return false;
    }


    public boolean deleteModule(String matricule) {
        Iterator<Module> iterator = modules.iterator();
        while (iterator.hasNext()) {
            Module m = iterator.next();
            if (m.getMatricule().equals(matricule)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Module> getAllModules() {
        return modules;
    }


    public List<Module> getModulesByUE(UniteEnseignement ue) {
        List<Module> result = new ArrayList<>();
        for (Module m : modules) {
            if (m.getUniteEnseignement() != null &&
                    m.getUniteEnseignement().getCode() == ue.getCode()) {
                result.add(m);
            }
        }
        return result;
    }
}
