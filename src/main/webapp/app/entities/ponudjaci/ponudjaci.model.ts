export interface IPonudjaci {
  id?: number;
  sifraPostupka?: number;
  sifraPonude?: number;
  nazivPonudjaca?: string;
}

export class Ponudjaci implements IPonudjaci {
  constructor(public id?: number, public sifraPostupka?: number, public sifraPonude?: number, public nazivPonudjaca?: string) {}
}

export function getPonudjaciIdentifier(ponudjaci: IPonudjaci): number | undefined {
  return ponudjaci.id;
}
