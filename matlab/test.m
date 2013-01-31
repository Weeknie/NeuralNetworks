reloadJar;
settings.set('runToTarget', true);
settings.set('solutionTarget', 10);
settings.set('block', true);
settings.set('threadCount', 5);
settings.set('verbose', false);

popSizes = 2:2:50;
output = zeros(size(popSizes, 2), 3);

index = 1;

for i = popSizes;
    for j = 1:(index + 10)
        disp(strcat('popSize: ', num2str(i), ', run #: ', num2str(j)));
        settings.set('circleMoveToXYDirection.popSize', i);
        api.run(settings);
        output(index, :) = output(index, :) + api.poll()';
    end
    index = index + 1;
end

plotData