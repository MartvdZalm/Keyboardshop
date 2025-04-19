
export interface ProductImage {
  id?: number;
  url: string;
}

export interface ProductImageSave {
  url: string;
  file: File;
  previewUrl: string;
}