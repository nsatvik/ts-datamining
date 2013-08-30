ts-datamining
=============

Time Series Data Mining Tool(TSDM Tool)
A time series is a sequence of
data points, measured typically at successive points in time spaced at uniform
time intervals. Time series analysis comprises methods for analyzing
time series data in order to extract meaningful statistics and other characteristics
of the data.In the context of statistics,the primary goal of time series
analysis is forecasting. In the context of signal processing it is used for signal
detection and estimation, while in the context of data mining, pattern
recognition and machine learning time series analysis can be used for clustering,
classication, query by content, anomaly detection as well as forecasting.
This project is aimed making a time series data mining tool which can be
used to accomplish the above goals.
This project mainly focuses on analyzing the sea and rainfall level time
series. The data sets considered belong to the rainfall data collected over
ten years in the six taluks of Chikkaballapura district of Karnataka. The
tool developed can be used to perform anomaly detection, forecasting, similarity
detection and temporal pattern detection. The performance of these
algorithms were tested on the above data sets and the results are presented.
The tool is developed using the model view control design pattern. The algorithms
are coded using Java using the object oriented paradigm. A web
based interface with chart visualisation is provided for the end user. This is
done using Java Server pages and Servlets. The aid of Google Charts API
is taken for plotting graphs. The tool used Git revision control system and
Github for online collaboration and code hosting.
The algorithms implemented in this tool require a set of user-dened
parameters that determine the accuracy of the results. The CUSUM and
Statistical approach in the Anomaly-Detection module discover anomalies in
the data sets. The Temporal Pattern Mining tool uses a tness threshold set
by the user and shows temporal patterns, and similarly, the Dynamic Time
Warping tool in the Similarity Module shows similarities among the time
series data sets. The Neural Network in the Forecasting module is the most
accurate among the algorithms with 60% accuracy.
