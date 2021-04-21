export interface IPonude {
  id?: number;
  sifraPostupka?: number;
  sifraPonude?: number;
  brojPartije?: number;
  nazivPonudjaca?: string;
  naziProizvodjaca?: string;
  zastceniNaziv?: string;
  ponudjenaVrijednost?: number;
  rokIsporuke?: number | null;
}

export class Ponude implements IPonude {
  constructor(
    public id?: number,
    public sifraPostupka?: number,
    public sifraPonude?: number,
    public brojPartije?: number,
    public nazivPonudjaca?: string,
    public naziProizvodjaca?: string,
    public zastceniNaziv?: string,
    public ponudjenaVrijednost?: number,
    public rokIsporuke?: number | null
  ) {}
}

export function getPonudeIdentifier(ponude: IPonude): number | undefined {
  return ponude.id;
}
