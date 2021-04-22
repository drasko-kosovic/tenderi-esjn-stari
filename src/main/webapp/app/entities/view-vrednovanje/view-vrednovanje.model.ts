export interface IViewVrednovanje {
  id?: number;
  sifraPostupka?: number | null;
  sifraPonude?: number | null;
  brojPartije?: number | null;
  nazivPonudjaca?: string | null;
  procijenjenaVrijednost?: number | null;
  ponudjenaVrijednost?: number | null;
  kolicina?: number | null;
  atc?: string | null;
  inn?: string | null;
  zastceniNaziv?: string | null;
  farmaceutskiOblikLijeka?: string | null;
  jacinaLijeka?: string | null;
  pakovanje?: string | null;
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
    public nazivPonudjaca?: string | null,
    public procijenjenaVrijednost?: number | null,
    public ponudjenaVrijednost?: number | null,
    public kolicina?: number | null,
    public atc?: string | null,
    public inn?: string | null,
    public zastceniNaziv?: string | null,
    public farmaceutskiOblikLijeka?: string | null,
    public jacinaLijeka?: string | null,
    public pakovanje?: string | null,
    public rokIsporuke?: number | null,
    public bodRok?: number | null,
    public bodCijena?: number | null,
    public bodUkupno?: number | null
  ) {}
}

export function getViewVrednovanjeIdentifier(viewVrednovanje: IViewVrednovanje): number | undefined {
  return viewVrednovanje.id;
}
