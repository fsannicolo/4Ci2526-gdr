public class Arciere extends Giocatore {
    
    public Arciere(String nome, int hp, int mana, int peso, Razza razza) {
        super(nome, hp, mana, peso, razza);  
    }

    @Override
    public int attacca(Giocatore target, int danno) {

        boolean haArma = false;

        // cerco nell'inventario se ho almeno un'arma da mischia
        for (Equip e : inventario) {

            if (e.getTipo() == TipoEquip.ArmaRanged) {
                haArma = true;
                break;
            }
        }

        // attacco solo se ho l'arma
        if (!haArma) {
            return 0;
        }

        // verifico se il target ha armature e le conto
        int armature = 0;
        for (Equip e : target.getInventario()) {
            
            if (e.getTipo() == TipoEquip.Armatura)
                armature++;
        }

        // modifico i puntivita del target
        int dannoFinale = danno / (armature + 1);
        target.setHp(target.getHp() - dannoFinale); 
        return dannoFinale;
    }

    @Override
    public void ricaricaMana() {
        
    }
}
