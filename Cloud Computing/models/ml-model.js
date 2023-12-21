const tfjs = require("@tensorflow/tfjs-node");


function loadConversionModel() {
  const modelUrl = 'https://storage.googleapis.com/camping-model-ml/model%20conversion/model_conversion.json';
  return tfjs.loadLayersModel(modelUrl);
}


function loadRecommendationModel() {
  const modelUrl = 'https://storage.googleapis.com/camping-model-ml/model%20conversion/model_Recommendation.json';
  return tfjs.loadLayersModel(modelUrl);
}

function predictConversion(model, rating) {

  const inputTensor = tfjs.tensor2d([[rating]]);  
  const result = model.predict(inputTensor).dataSync();
  return result;
}

function predictRecommendation(model, inputFeatures) {
  const inputTensor = tfjs.tensor2d([inputFeatures]);  
  const result = model.predict(inputTensor).dataSync();
  return result;
}

async function makePredictions() {
  const conversionModel = await loadConversionModel();
  const recommendationModel = await loadRecommendationModel();


  const rating = 4.5;
  const conversionResult = predictConversion(conversionModel, rating);
  console.log('Conversion Model Result:', conversionResult);


  const inputFeatures = [/* Add your input features here */];
  const recommendationResult = predictRecommendation(recommendationModel, inputFeatures);
  console.log('Recommendation Model Result:', recommendationResult);
}


makePredictions();

module.exports = { loadConversionModel, loadRecommendationModel, predictConversion, predictRecommendation };
