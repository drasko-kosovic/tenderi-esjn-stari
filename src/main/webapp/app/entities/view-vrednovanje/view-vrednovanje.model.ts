export interface IViewVrednovanje {
  id?: number;
  sifraPostupka?: number | null;
  sifraPonude?: number | null;
  brojPartije?: number | null;
  atc?: string | null;
  inn?: string | null;
  zastceniNaziv?: string | null;
  nazivProizvodjaca?: string;
  farmaceutskiOblikLijeka?: string | null;
  jacinaLijeka?: string | null;
  pakovanje?: string | null;
  kolicina?: number | null;
  trazenaJedinicnaCijena?: number | null;
  procijenjenaVrijednost?: number | null;
  ponudjenaJedinicnaCijena?: number | null;
  ponudjenaVrijednost?: number | null;
  nazivPonudjaca?: string | null;
  bodRok?: number | null;
  rokIsporuke?: number | null;
  bodCijena?: number | null;
  bodUkupno?: number | null;
}

export class ViewVrednovanje implements IViewVrednovanje {
  constructor(
    public id?: number,
    public sifraPostupka?: number | null,
    public sifraPonude?: number | null,
    public brojPartije?: number | null,
    public atc?: string | null,
    public inn?: string | null,
    public zastceniNaziv?: string | null,
    public nazivProizvodjaca?: string,
    public farmaceutskiOblikLijeka?: string | null,
    public jacinaLijeka?: string | null,
    public pakovanje?: string | null,
    public kolicina?: number | null,
    public trazenaJedinicnaCijena?: number | null,
    public procijenjenaVrijednost?: number | null,
    public ponudjenaJedinicnaCijena?: number | null,
    public ponudjenaVrijednost?: number | null,
    public nazivPonudjaca?: string | null,
    public bodRok?: number | null,
    public rokIsporuke?: number | null,
    public bodCijena?: number | null,
    public bodUkupno?: number | null
  ) {}
}

export function getViewVrednovanjeIdentifier(viewVrednovanje: IViewVrednovanje): number | undefined {
  return viewVrednovanje.id;
}
