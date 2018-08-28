export interface Jewel {
  businessId: number;
  name: string;
  sku: string;
  type: string;

  shortDescription: string;
  shortDescriptionEdited: string;
  description: string;
  descriptionEdited: string;
  taxClass: string;
  inStorage: string;
  storage: string;
  weight: number;
  weightEdited: number;
  length: number;
  lengthEdited: number;
  width: number;
  widthEdited: number;
  height: number;
  heightEdited: number;
  promoPrice: number;
  promoPriceEdited: number;
  price: number;
  priceEdited: number;
  category: string;
  categoryEdited: string;
  tags: string;
  tagsEdited: string;
  attribute1Name: string;
  attribute1Value: string;

  images: object[];
}
