  public boolean isValidMove(GameBoard b, Move m, int side) {
    int x1 = m.x1;
    int y1 = m.y1;
      if ((x1 == 0 && y1 == 0) || (x1 == 0 && y1 == 7) || (x1 == 7 && y1 == 0) || (x1 == 7 && y1 == 7)) { //cant add chips into corners
        return false;
      }
      if (side == 0) { //black side, cant add chip to left/right goalzone
        if ((x1 == 0) || (x1 == 7)) {
          return false;
        }
      }
      if (side == 1) { //white side, cant add chip to top/bot goalzone
        if ((y1 == 0) || (y1 == 7)) {
          return false;
        }
      }
      if (m.moveKind == 1) {  // add move
        if (b.hasChip(x1, y1) || b.getCount(side) == 10) {
          return false;
        }
        else {
          int count = 0;
          int x3 = 0;
          int y3 = 0;
          for (int x = -1; x<2; x++) {
            for (int y = -1; y<2; y++) {
              if (x != 0 || y != 0) {
                if (b.sideHasChip(x1+x, y1+y, side)) {
                  count++;
                  x3 = x1+x;
                  y3 = y1+y;
                    for (int i = -1; i < 2; i++) {
                      for (int j = -1; j < 2; j++) {
                        if (i != 0 || j != 0) {
                          if (b.sideHasChip(x3+i, y3+j, side)) {
                            return false;
                          }
                        }
                      }
                    }
                }
              }
            }
          }
          if (count >= 2) {
            return false;
          }
          return true;
        }
      }   
      else if (m.moveKind == 2) {  // step move
        int x2 = m.x2;
        int y2 = m.y2;
        if (b.hasChip(x1, y1) || !b.sideHasChip(x2, y2, side) || b.getCount(side) < 10) {
          return false;
        }
        else {
          int count = 0;
          int x3 = 0;
          int y3 = 0;
          for (int x = -1; x<2; x++) {
            for (int y = -1; y<2; y++) {
              if (x != 0 || y != 0) {
                if (b.sideHasChip(x1+x, y1+y, side)) {
                  count++;
                  x3 = x1+x;
                  y3 = y1+y;
                    for (int i = -1; i < 2; i++) {
                      for (int j = -1; j < 2; j++) {
                        if (i != 0 || j != 0) {
                          if (b.sideHasChip(x3+i, y3+j, side)) {
                            return false;
                          }
                        }
                      }
                    }
              }
              }
            }
          }
          if (count >= 2) {
            return false;
          }
          return true;
        }
      }
    return true;
  }