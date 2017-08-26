main() {
int S, E, O, N, R, D, Y;
for (S = 1;S <= 9;S++)
 for (E = 0;E <= 9;E++)
  for (O = 0;O <= 9;O++)
   for (N = 0;N <= 9;N++)
    for (R = 0;R <= 9;R++)
     for (D = 0;D <= 9;D++)
      for (Y = 0;Y <= 9;Y++)
       if (S != E && S != O && S != N && S != R && S != D && S != Y &&
           E != O && E != N && E != R && E != D && E != Y && 
           O != N && O != R && O != D && O != Y &&
           N != R && N != D && N != Y &&
           R != D && R != Y &&
           D != Y &&
           S*1000+E*100+N*10+D+1000+O*100+R*10+E == 10000+O*1000+N*100+E*10+Y)
        printf("   %1d%1d%1d%1d\n + 1%1d%1d%1d\n________\n  1%1d%1d%1d%1d\n\n",
                S,E,N,D,O,R,E,O,N,E,Y);
} /* main */
