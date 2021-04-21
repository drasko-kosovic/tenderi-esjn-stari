import * as dayjs from 'dayjs';

export interface IPonude {
  id?: number;
  sifraPostupka?: number;
  sifraPonude?: number;
  brojPartije?: number;
  nazivPonudjaca?: string;
  naziProizvodjaca?: string;
  zastceniNaziv?: string;
  ponudjenaVrijednost?: number;
  ponudjenaJedinicnaCijena?: number;
  rokIsporuke?: number | null;
  brojUgovora?: string | null;
  datumUgovora?: dayjs.Dayjs | null;
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
    public ponudjenaJedinicnaCijena?: number,
    public rokIsporuke?: number | null,
    public brojUgovora?: string | null,
    public datumUgovora?: dayjs.Dayjs | null
  ) {}
}

export function getPonudeIdentifier(ponude: IPonude): number | undefined {
  return ponude.id;
}
