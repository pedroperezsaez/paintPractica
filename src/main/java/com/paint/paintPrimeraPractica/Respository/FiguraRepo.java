package com.paint.paintPrimeraPractica.Respository;

import com.paint.paintPrimeraPractica.model.Figura;

public interface FiguraRepo {
    public Figura buscarFigura(int id);
    public Figura agregarFigura(Figura figura);
}
