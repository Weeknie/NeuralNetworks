hold on;

%[x, y] = meshgrid(logspace(-2, log10(400), 50), logspace(-2, log10(400), 50));
[x, y] = meshgrid(-400:10:400, -400:10:400);
distance = sqrt(x.^2 + y.^2);
fitness1 = 1./distance;
%surf(x, y, fitness1);

maxDistance = sqrt(200^2 + 200^2);
%fitness2 = min(abs(maxDistance - distance), maxDistance);
fitness2 = max(maxDistance - distance, 0);
%surf(x, y, log(fitness2));
surf(x, y, fitness2);
hold off