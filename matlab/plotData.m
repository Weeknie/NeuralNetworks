labelx = 'Population Size';

subplot(2, 2, [1 2]);
data = output(:, 1)./(output(:, 1) + output(:, 2)) * 100;
plot(popSizes, data)
xlabel(labelx);
ylabel('% Correct');

subplot(2, 2, 3);
data = output(:, 2)./(output(:, 1) + output(:, 2)) * 100;
plot(popSizes, data)
xlabel(labelx);
ylabel('% Non Converging');

subplot(2, 2, 4);
data = output(:, 3)./(output(:, 1) + output(:, 2));
plot(popSizes, data)
xlabel(labelx);
ylabel('Average generations');