export interface ISpecifikacija {
  id?: number;
  sifraPostupka?: number;
  brojPartije?: number;
  atc?: string;
  inn?: string | null;
  farmaceutskiOblikLijeka?: string | null;
  jacinaLijeka?: string | null;
  trazenaKolicina?: number;
  pakovanje?: string | null;
  procijenjenaVrijednost?: number;
  trazenaJedinicnaCijena?: number;
}

export class Specifikacija implements ISpecifikacija {
  constructor(
    public id?: number,
    public sifraPostupka?: number,
    public brojPartije?: number,
    public atc?: string,
    public inn?: string | null,
    public farmaceutskiOblikLijeka?: string | null,
    public jacinaLijeka?: string | null,
    public trazenaKolicina?: number,
    public pakovanje?: string | null,
    public procijenjenaVrijednost?: number,
    public trazenaJedinicnaCijena?: number
  ) {}
}

export function getSpecifikacijaIdentifier(specifikacija: ISpecifikacija): number | undefined {
  return specifikacija.id;
}
