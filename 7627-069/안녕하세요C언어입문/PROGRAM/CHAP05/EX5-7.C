 main() {
 float kor, eng, math;
 float ave;
 printf("Korean? ");
 scanf("%f",&kor);
 printf("English? ");
 scanf("%f",&eng);
 printf("Math? ");
 scanf("%f",&math);
 if (kor > 10.0f || kor < 0.0f || eng > 10.0f || eng < 0.0f 
                 || math > 10.0f || math < 0.0f)
    printf("Data error!!\n");
 else {
    ave = (kor+eng+math)/3.0f;
    printf("The average = %f\n",ave);
    printf("The grade is ");
    switch((int)ave) {
    case 10: 
    case 9: 
    case 8: printf("A\n");
            break;
    case 7: 
    case 6: 
    case 5: printf("B\n");
            break;
    case 4: 
    case 3: 
    case 2: printf("C\n");
            break;
    case 1: 
    case 0: printf("F\n");
     } /* switch */
  } /* else */
 } /* main */

