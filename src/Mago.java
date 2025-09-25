public class Mago extends Giocatore {
    
    private final int MANA_ATT = 20;

    public Mago(String nome, int hp, int mana, int peso, Razza razza) {
        super(nome, hp, mana, peso, razza);  
    }

    @Override
    public int attacca(Giocatore target, int danno) {
        
        // verifico se il target ha armature e le conto
        int armature = 0;
        for (Equip e : target.getInventario()) {
            
            if (e.getTipo() == TipoEquip.Armatura)
                armature++;
        }

        // verificare se c'è abbastanza mana e aggiornare
        if (mana - MANA_ATT >= 0) {
            mana -= MANA_ATT;

            // guerriero e arciere subisce il doppio di danno magico
            int multi = 1;
            if (target instanceof Guerriero || target instanceof Arciere)
                multi = 2;

            // modifico i puntivita del target
            int dannoFinale = multi * danno / (armature + 1);
            target.setHp(target.getHp() - dannoFinale); 

            // se ho sconfitto qualcuno, ricarico
            if (target.isMorto()) {
                ricaricaMana();
            }

            return dannoFinale;
        }

        return 0;
    }

    @Override
    public void ricaricaMana() {

        mana = MANA_MAX;
    }
}
