export interface ISpecifikacija {
  id?: number;
  sifraPostupka?: number;
  brojPartije?: number;
  atc?: string;
  inn?: string | null;
  farmaceutskiOblikLijeka?: string | null;
  jacinaLijeka?: string | null;
  kolicina?: number;
  pakovanje?: string | null;
  procijenjenaVrijednost?: number;
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
    public kolicina?: number,
    public pakovanje?: string | null,
    public procijenjenaVrijednost?: number
  ) {}
}

export function getSpecifikacijaIdentifier(specifikacija: ISpecifikacija): number | undefined {
  return specifikacija.id;
}
